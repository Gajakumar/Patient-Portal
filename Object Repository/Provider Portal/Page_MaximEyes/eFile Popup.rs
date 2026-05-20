<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>eFile Popup</name>
   <tag></tag>
   <elementGuidId>a75d00d0-72a2-4605-9515-99e2b5fb4312</elementGuidId>
   <selectorCollection>
      <entry>
         <key>CSS</key>
         <value>#16671 > div.window.flat</value>
      </entry>
      <entry>
         <key>XPATH</key>
         <value>//div[(text() = 'ELECTRONIC FILES' or . = 'ELECTRONIC FILES')]</value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <smartLocatorCollection>
      <entry>
         <key>SMART_LOCATOR</key>
         <value>internal:text=&quot;ELECTRONIC FILES File Name Category Date ATTACH&quot;i</value>
      </entry>
   </smartLocatorCollection>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>true</useRalativeImagePath>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>div</value>
      <webElementGuid>d06d606d-34c3-4425-b539-d6cc1a9239eb</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>class</name>
      <type>Main</type>
      <value>window flat</value>
      <webElementGuid>05f1c6be-5e26-49c6-8fc3-10c61de0eefb</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>
                
                    
                    
                    
                        ELECTRONIC FILES
                    
                
                
           
        
            
                
                    
                        
                            
                                
                                    
                                        
                                            
                                                 
                                            
                                        
                                    
                                    File Name
                                    Category
                                    Date
                                
                            
                            
                                                        
                        
                    
                
            
        
        
            
                
                    
                
            
        
        


    $(document).ready(function () {
        $('#attachButton').click(function () {
            var checkedItems = getCheckedItems();
            if (!checkedItems) {
                $(getPopupCloseButton($('#extDivEfilePopup'))).click();
                return;
            }
            // Determine the correct attachments container based on which screen is visible
            var attachmentsContainer;
            if ($('#forward-mainBodySecureMessage').is(':visible')) {
                attachmentsContainer = $('#Forward-attachments-container');
            } else {
                attachmentsContainer = $('#Compose-attachments-container');
            }
            var currentAttachments = attachmentsContainer.find('button').length;
            if (currentAttachments + checkedItems.length > 5) {
                showWarningBar('Maximum 5 attachments are allowed');
                stopBusyIndicator();
                return;
            }
            var patid = $('#hiddenPatId').val();

            getJSONData('GetEFilesDataOfPatientForProviderPortal', 'FileManager', { customerId: patid, fileGuids: checkedItems }, function (data) {
                if (data != null) {
                    if (!data.success) {
                        showWarningBar(data.message); // Show warning message from server
                    }

                    if (data.files &amp;&amp; data.files.length > 0) {
                        handleEfileAttachment(data.files, attachmentsContainer);
                    }

                    stopBusyIndicator();
                    $(getPopupCloseButton($('#extDivEfilePopup'))).click();
                }
                else {
                    showErrorBar('Something went wrong.');
                }
            }, true);//PravinC 08.29.2024

        });
    });

    function getCheckedItems() {
        var checkedItems = [];
        $('tbody input[type=&quot;checkbox&quot;]:checked').each(function () {
            var itemName = $(this).val();
            checkedItems.push(itemName);
        });
        if (checkedItems.length === 0) {
            return;
        }
        return checkedItems;
    }

    function handleEfileAttachment(files, attachmentsContainer) { //PravinC 08.29.2024 #46657
        var warningPopup = &quot;warningPopup&quot;;
        var btnobj = [{ btnId: &quot;btnCancelAuthLink&quot;, btnText: 'CANCEL' }];
        var totalSize = 0;
        var fileSize = 0;

        // Check if maximum attachments limit is reached
        var currentAttachments = attachmentsContainer.find('button').length;
        if (currentAttachments + files.length > 5) {
            showWarningBar('Maximum 5 attachments are allowed');
            stopBusyIndicator();
            return;
        }

        // Calculate total size of existing attachments
        attachmentsContainer.find('button').each(function () {
            fileSize = parseFloat($(this).attr('data-file-size'));
            if (!isNaN(fileSize)) {
                totalSize += fileSize;
            }
        });

        // Loop through files to attach
        for (var i = 0; i &lt; files.length; i++) {
            var file = files[i];
            var btnValue = file.FriendlyName;
            var found = false;

            // Check if the file is already attached (eFile duplicates)
            attachmentsContainer.find('button').each(function () {
                if ($(this).attr('value') === btnValue) {
                    found = true;
                    return false;
                }
            });

            // Also check local file attachments (added via data-file-name attribute)
            if (!found) {
                attachmentsContainer.find('button[data-file-name]').each(function () {
                    var localFileName = $(this).attr('data-file-name');
                    if (localFileName === btnValue) {
                        found = true;
                        return false; // break the loop
                    }
                });
            }

            // Also check forwarded attachments (buttons inside span elements with data-file-name)- foward case 
            if (!found) {
                attachmentsContainer.find('span button[data-file-name]').each(function () {
                    var forwardedFileName = $(this).attr('data-file-name');
                    if (forwardedFileName === btnValue) {
                        found = true;
                        return false; // break the loop
                    }
                });
            }

            if (found) {
                showWarningBar(&quot;This e-file has already been shared&quot;);
                continue;
            }

            var fileName = file.FriendlyName.trim();
            var fileExtension = '';

            // Check if file name exceeds character limit
            if (fileName.length > 15) {
                fileExtension = fileName.split('.').pop();
                fileName = fileName.substring(0, 15 - fileExtension.length - 1).trim() + '.' + fileExtension;
            }

            // Check if file size is 0 bytes
            if (file.FileSize === 0 || file.FileSize === undefined || file.FileSize === null) {
                showWarningBar('Attached files must be greater than 0 bytes');
                stopBusyIndicator();
                return;
            }

            // Check total file size including new file
            totalSize += file.FileSize;
            if (totalSize > 26214400) {
                showWarningBar(&quot;The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB.&quot;);
                stopBusyIndicator();
                return;
            }

            // Check file format
            var allowedFormats = ['jpg', 'pdf', 'doc', 'png', 'txt', 'xml'];
            var fileExtension = file.Extension.toLowerCase();

            if (allowedFormats.indexOf(fileExtension) === -1) {
                newShowConfirmationBarWithCustomButton('warringPopup', 'Unsupported file format: ' + fileExtension, btnobj, &quot;width50&quot;, true);
                stopBusyIndicator();
                return;
            }

            // Create attachment button
            var button = $('&lt;button class=&quot;font16 marR10&quot; value=&quot;' + btnValue + '&quot; data-file=&quot;' + file.sToBase64StringOfEfile + '&quot; data-file-size=&quot;' + file.FileSize + '&quot;>' + fileName + '&amp;nbsp;&amp;nbsp;&lt;span class=&quot;mif-delete font18 marL06 marB05&quot;>&lt;/span>&lt;/button>');
            $('#Compose-no-attachments').hide();
            attachmentsContainer.append(button);
        }
    }




    function PreviewEfileForPP(RecordGuid, Source, FileId, FileName) {
        var patid = $('#hiddenPatId').val();
        var param = {};
        param['Id'] = patid;
        param['RecordGuid'] = RecordGuid;
        param['Source'] = Source;
        param[&quot;FileAction&quot;] = &quot;Edit&quot;;
        param[&quot;IsEfilePreview&quot;] = true; //if file preview then true and it get single file from DB

        param[&quot;LstEFile&quot;] = [];
        var fileName = FileName;
        var fileNameExtObj = Efile_GetFileName_FileExtenstion_for_PP(fileName);

        param[&quot;LstEFile&quot;][0] = {};
        param[&quot;LstEFile&quot;][0][&quot;Name&quot;] = FileId;
        if (fileNameExtObj != null) {
            param[&quot;LstEFile&quot;][0][&quot;FriendlyName&quot;] = fileNameExtObj.fileName;
            param[&quot;LstEFile&quot;][0][&quot;Extension&quot;] = fileNameExtObj.fileExt;
        }
        else {
            param[&quot;LstEFile&quot;][0][&quot;FriendlyName&quot;] = &quot;&quot;;
            param[&quot;LstEFile&quot;][0][&quot;Extension&quot;] = &quot;&quot;;
        }
        ShowModalPopup('', 'popup-90 grad-gray-white', 'AddEditFileMetadata/FileManager', param, null);
    }

    function Efile_GetFileName_FileExtenstion_for_PP(fileName) {
        var extIndex = fileName.lastIndexOf('.');
        var fExt = null;

        if (extIndex != -1) {
            fExt = fileName.substring(extIndex + 1, fileName.length)
            fileName = fileName.substring(0, extIndex);

            return { fileName: fileName, fileExt: fExt.toLowerCase() };
        }
        return null;
    }


            </value>
      <webElementGuid>a73912bf-1a79-4ad7-aa0d-2a44642e5671</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>id(&quot;16671&quot;)/div[@class=&quot;window flat&quot;]</value>
      <webElementGuid>5a1ee468-1b65-49b3-b32e-6151ed636219</webElementGuid>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:idRelative</name>
      <type>Main</type>
      <value>(.//*[normalize-space(text()) and normalize-space(.)='»'])[2]/following::div[6]</value>
      <webElementGuid>3b075b6e-cdad-4f91-9151-5eed5a15f536</webElementGuid>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:neighbor</name>
      <type>Main</type>
      <value>(.//*[normalize-space(text()) and normalize-space(.)='»'])[2]/following::div[6]</value>
      <webElementGuid>ff953c24-93ca-4279-8ded-ce5873003a98</webElementGuid>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:neighbor</name>
      <type>Main</type>
      <value>(.//*[normalize-space(text()) and normalize-space(.)='Place call'])[1]/following::div[8]</value>
      <webElementGuid>bcb72744-8c6c-49c0-94e2-4e8942169f31</webElementGuid>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:position</name>
      <type>Main</type>
      <value>//div[50]/div</value>
      <webElementGuid>7910a953-8ed6-4d72-8bba-3a6dcb22d80b</webElementGuid>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:customAttributes</name>
      <type>Main</type>
      <value>//div[(text() = concat(&quot;
                
                    
                    
                    
                        ELECTRONIC FILES
                    
                
                
           
        
            
                
                    
                        
                            
                                
                                    
                                        
                                            
                                                 
                                            
                                        
                                    
                                    File Name
                                    Category
                                    Date
                                
                            
                            
                                                        
                        
                    
                
            
        
        
            
                
                    
                
            
        
        


    $(document).ready(function () {
        $(&quot; , &quot;'&quot; , &quot;#attachButton&quot; , &quot;'&quot; , &quot;).click(function () {
            var checkedItems = getCheckedItems();
            if (!checkedItems) {
                $(getPopupCloseButton($(&quot; , &quot;'&quot; , &quot;#extDivEfilePopup&quot; , &quot;'&quot; , &quot;))).click();
                return;
            }
            // Determine the correct attachments container based on which screen is visible
            var attachmentsContainer;
            if ($(&quot; , &quot;'&quot; , &quot;#forward-mainBodySecureMessage&quot; , &quot;'&quot; , &quot;).is(&quot; , &quot;'&quot; , &quot;:visible&quot; , &quot;'&quot; , &quot;)) {
                attachmentsContainer = $(&quot; , &quot;'&quot; , &quot;#Forward-attachments-container&quot; , &quot;'&quot; , &quot;);
            } else {
                attachmentsContainer = $(&quot; , &quot;'&quot; , &quot;#Compose-attachments-container&quot; , &quot;'&quot; , &quot;);
            }
            var currentAttachments = attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).length;
            if (currentAttachments + checkedItems.length > 5) {
                showWarningBar(&quot; , &quot;'&quot; , &quot;Maximum 5 attachments are allowed&quot; , &quot;'&quot; , &quot;);
                stopBusyIndicator();
                return;
            }
            var patid = $(&quot; , &quot;'&quot; , &quot;#hiddenPatId&quot; , &quot;'&quot; , &quot;).val();

            getJSONData(&quot; , &quot;'&quot; , &quot;GetEFilesDataOfPatientForProviderPortal&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;FileManager&quot; , &quot;'&quot; , &quot;, { customerId: patid, fileGuids: checkedItems }, function (data) {
                if (data != null) {
                    if (!data.success) {
                        showWarningBar(data.message); // Show warning message from server
                    }

                    if (data.files &amp;&amp; data.files.length > 0) {
                        handleEfileAttachment(data.files, attachmentsContainer);
                    }

                    stopBusyIndicator();
                    $(getPopupCloseButton($(&quot; , &quot;'&quot; , &quot;#extDivEfilePopup&quot; , &quot;'&quot; , &quot;))).click();
                }
                else {
                    showErrorBar(&quot; , &quot;'&quot; , &quot;Something went wrong.&quot; , &quot;'&quot; , &quot;);
                }
            }, true);//PravinC 08.29.2024

        });
    });

    function getCheckedItems() {
        var checkedItems = [];
        $(&quot; , &quot;'&quot; , &quot;tbody input[type=&quot;checkbox&quot;]:checked&quot; , &quot;'&quot; , &quot;).each(function () {
            var itemName = $(this).val();
            checkedItems.push(itemName);
        });
        if (checkedItems.length === 0) {
            return;
        }
        return checkedItems;
    }

    function handleEfileAttachment(files, attachmentsContainer) { //PravinC 08.29.2024 #46657
        var warningPopup = &quot;warningPopup&quot;;
        var btnobj = [{ btnId: &quot;btnCancelAuthLink&quot;, btnText: &quot; , &quot;'&quot; , &quot;CANCEL&quot; , &quot;'&quot; , &quot; }];
        var totalSize = 0;
        var fileSize = 0;

        // Check if maximum attachments limit is reached
        var currentAttachments = attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).length;
        if (currentAttachments + files.length > 5) {
            showWarningBar(&quot; , &quot;'&quot; , &quot;Maximum 5 attachments are allowed&quot; , &quot;'&quot; , &quot;);
            stopBusyIndicator();
            return;
        }

        // Calculate total size of existing attachments
        attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).each(function () {
            fileSize = parseFloat($(this).attr(&quot; , &quot;'&quot; , &quot;data-file-size&quot; , &quot;'&quot; , &quot;));
            if (!isNaN(fileSize)) {
                totalSize += fileSize;
            }
        });

        // Loop through files to attach
        for (var i = 0; i &lt; files.length; i++) {
            var file = files[i];
            var btnValue = file.FriendlyName;
            var found = false;

            // Check if the file is already attached (eFile duplicates)
            attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).each(function () {
                if ($(this).attr(&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;) === btnValue) {
                    found = true;
                    return false;
                }
            });

            // Also check local file attachments (added via data-file-name attribute)
            if (!found) {
                attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button[data-file-name]&quot; , &quot;'&quot; , &quot;).each(function () {
                    var localFileName = $(this).attr(&quot; , &quot;'&quot; , &quot;data-file-name&quot; , &quot;'&quot; , &quot;);
                    if (localFileName === btnValue) {
                        found = true;
                        return false; // break the loop
                    }
                });
            }

            // Also check forwarded attachments (buttons inside span elements with data-file-name)- foward case 
            if (!found) {
                attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;span button[data-file-name]&quot; , &quot;'&quot; , &quot;).each(function () {
                    var forwardedFileName = $(this).attr(&quot; , &quot;'&quot; , &quot;data-file-name&quot; , &quot;'&quot; , &quot;);
                    if (forwardedFileName === btnValue) {
                        found = true;
                        return false; // break the loop
                    }
                });
            }

            if (found) {
                showWarningBar(&quot;This e-file has already been shared&quot;);
                continue;
            }

            var fileName = file.FriendlyName.trim();
            var fileExtension = &quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;;

            // Check if file name exceeds character limit
            if (fileName.length > 15) {
                fileExtension = fileName.split(&quot; , &quot;'&quot; , &quot;.&quot; , &quot;'&quot; , &quot;).pop();
                fileName = fileName.substring(0, 15 - fileExtension.length - 1).trim() + &quot; , &quot;'&quot; , &quot;.&quot; , &quot;'&quot; , &quot; + fileExtension;
            }

            // Check if file size is 0 bytes
            if (file.FileSize === 0 || file.FileSize === undefined || file.FileSize === null) {
                showWarningBar(&quot; , &quot;'&quot; , &quot;Attached files must be greater than 0 bytes&quot; , &quot;'&quot; , &quot;);
                stopBusyIndicator();
                return;
            }

            // Check total file size including new file
            totalSize += file.FileSize;
            if (totalSize > 26214400) {
                showWarningBar(&quot;The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB.&quot;);
                stopBusyIndicator();
                return;
            }

            // Check file format
            var allowedFormats = [&quot; , &quot;'&quot; , &quot;jpg&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;pdf&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;doc&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;png&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;txt&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;xml&quot; , &quot;'&quot; , &quot;];
            var fileExtension = file.Extension.toLowerCase();

            if (allowedFormats.indexOf(fileExtension) === -1) {
                newShowConfirmationBarWithCustomButton(&quot; , &quot;'&quot; , &quot;warringPopup&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;Unsupported file format: &quot; , &quot;'&quot; , &quot; + fileExtension, btnobj, &quot;width50&quot;, true);
                stopBusyIndicator();
                return;
            }

            // Create attachment button
            var button = $(&quot; , &quot;'&quot; , &quot;&lt;button class=&quot;font16 marR10&quot; value=&quot;&quot; , &quot;'&quot; , &quot; + btnValue + &quot; , &quot;'&quot; , &quot;&quot; data-file=&quot;&quot; , &quot;'&quot; , &quot; + file.sToBase64StringOfEfile + &quot; , &quot;'&quot; , &quot;&quot; data-file-size=&quot;&quot; , &quot;'&quot; , &quot; + file.FileSize + &quot; , &quot;'&quot; , &quot;&quot;>&quot; , &quot;'&quot; , &quot; + fileName + &quot; , &quot;'&quot; , &quot;&amp;nbsp;&amp;nbsp;&lt;span class=&quot;mif-delete font18 marL06 marB05&quot;>&lt;/span>&lt;/button>&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;#Compose-no-attachments&quot; , &quot;'&quot; , &quot;).hide();
            attachmentsContainer.append(button);
        }
    }




    function PreviewEfileForPP(RecordGuid, Source, FileId, FileName) {
        var patid = $(&quot; , &quot;'&quot; , &quot;#hiddenPatId&quot; , &quot;'&quot; , &quot;).val();
        var param = {};
        param[&quot; , &quot;'&quot; , &quot;Id&quot; , &quot;'&quot; , &quot;] = patid;
        param[&quot; , &quot;'&quot; , &quot;RecordGuid&quot; , &quot;'&quot; , &quot;] = RecordGuid;
        param[&quot; , &quot;'&quot; , &quot;Source&quot; , &quot;'&quot; , &quot;] = Source;
        param[&quot;FileAction&quot;] = &quot;Edit&quot;;
        param[&quot;IsEfilePreview&quot;] = true; //if file preview then true and it get single file from DB

        param[&quot;LstEFile&quot;] = [];
        var fileName = FileName;
        var fileNameExtObj = Efile_GetFileName_FileExtenstion_for_PP(fileName);

        param[&quot;LstEFile&quot;][0] = {};
        param[&quot;LstEFile&quot;][0][&quot;Name&quot;] = FileId;
        if (fileNameExtObj != null) {
            param[&quot;LstEFile&quot;][0][&quot;FriendlyName&quot;] = fileNameExtObj.fileName;
            param[&quot;LstEFile&quot;][0][&quot;Extension&quot;] = fileNameExtObj.fileExt;
        }
        else {
            param[&quot;LstEFile&quot;][0][&quot;FriendlyName&quot;] = &quot;&quot;;
            param[&quot;LstEFile&quot;][0][&quot;Extension&quot;] = &quot;&quot;;
        }
        ShowModalPopup(&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;popup-90 grad-gray-white&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;AddEditFileMetadata/FileManager&quot; , &quot;'&quot; , &quot;, param, null);
    }

    function Efile_GetFileName_FileExtenstion_for_PP(fileName) {
        var extIndex = fileName.lastIndexOf(&quot; , &quot;'&quot; , &quot;.&quot; , &quot;'&quot; , &quot;);
        var fExt = null;

        if (extIndex != -1) {
            fExt = fileName.substring(extIndex + 1, fileName.length)
            fileName = fileName.substring(0, extIndex);

            return { fileName: fileName, fileExt: fExt.toLowerCase() };
        }
        return null;
    }


            &quot;) or . = concat(&quot;
                
                    
                    
                    
                        ELECTRONIC FILES
                    
                
                
           
        
            
                
                    
                        
                            
                                
                                    
                                        
                                            
                                                 
                                            
                                        
                                    
                                    File Name
                                    Category
                                    Date
                                
                            
                            
                                                        
                        
                    
                
            
        
        
            
                
                    
                
            
        
        


    $(document).ready(function () {
        $(&quot; , &quot;'&quot; , &quot;#attachButton&quot; , &quot;'&quot; , &quot;).click(function () {
            var checkedItems = getCheckedItems();
            if (!checkedItems) {
                $(getPopupCloseButton($(&quot; , &quot;'&quot; , &quot;#extDivEfilePopup&quot; , &quot;'&quot; , &quot;))).click();
                return;
            }
            // Determine the correct attachments container based on which screen is visible
            var attachmentsContainer;
            if ($(&quot; , &quot;'&quot; , &quot;#forward-mainBodySecureMessage&quot; , &quot;'&quot; , &quot;).is(&quot; , &quot;'&quot; , &quot;:visible&quot; , &quot;'&quot; , &quot;)) {
                attachmentsContainer = $(&quot; , &quot;'&quot; , &quot;#Forward-attachments-container&quot; , &quot;'&quot; , &quot;);
            } else {
                attachmentsContainer = $(&quot; , &quot;'&quot; , &quot;#Compose-attachments-container&quot; , &quot;'&quot; , &quot;);
            }
            var currentAttachments = attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).length;
            if (currentAttachments + checkedItems.length > 5) {
                showWarningBar(&quot; , &quot;'&quot; , &quot;Maximum 5 attachments are allowed&quot; , &quot;'&quot; , &quot;);
                stopBusyIndicator();
                return;
            }
            var patid = $(&quot; , &quot;'&quot; , &quot;#hiddenPatId&quot; , &quot;'&quot; , &quot;).val();

            getJSONData(&quot; , &quot;'&quot; , &quot;GetEFilesDataOfPatientForProviderPortal&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;FileManager&quot; , &quot;'&quot; , &quot;, { customerId: patid, fileGuids: checkedItems }, function (data) {
                if (data != null) {
                    if (!data.success) {
                        showWarningBar(data.message); // Show warning message from server
                    }

                    if (data.files &amp;&amp; data.files.length > 0) {
                        handleEfileAttachment(data.files, attachmentsContainer);
                    }

                    stopBusyIndicator();
                    $(getPopupCloseButton($(&quot; , &quot;'&quot; , &quot;#extDivEfilePopup&quot; , &quot;'&quot; , &quot;))).click();
                }
                else {
                    showErrorBar(&quot; , &quot;'&quot; , &quot;Something went wrong.&quot; , &quot;'&quot; , &quot;);
                }
            }, true);//PravinC 08.29.2024

        });
    });

    function getCheckedItems() {
        var checkedItems = [];
        $(&quot; , &quot;'&quot; , &quot;tbody input[type=&quot;checkbox&quot;]:checked&quot; , &quot;'&quot; , &quot;).each(function () {
            var itemName = $(this).val();
            checkedItems.push(itemName);
        });
        if (checkedItems.length === 0) {
            return;
        }
        return checkedItems;
    }

    function handleEfileAttachment(files, attachmentsContainer) { //PravinC 08.29.2024 #46657
        var warningPopup = &quot;warningPopup&quot;;
        var btnobj = [{ btnId: &quot;btnCancelAuthLink&quot;, btnText: &quot; , &quot;'&quot; , &quot;CANCEL&quot; , &quot;'&quot; , &quot; }];
        var totalSize = 0;
        var fileSize = 0;

        // Check if maximum attachments limit is reached
        var currentAttachments = attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).length;
        if (currentAttachments + files.length > 5) {
            showWarningBar(&quot; , &quot;'&quot; , &quot;Maximum 5 attachments are allowed&quot; , &quot;'&quot; , &quot;);
            stopBusyIndicator();
            return;
        }

        // Calculate total size of existing attachments
        attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).each(function () {
            fileSize = parseFloat($(this).attr(&quot; , &quot;'&quot; , &quot;data-file-size&quot; , &quot;'&quot; , &quot;));
            if (!isNaN(fileSize)) {
                totalSize += fileSize;
            }
        });

        // Loop through files to attach
        for (var i = 0; i &lt; files.length; i++) {
            var file = files[i];
            var btnValue = file.FriendlyName;
            var found = false;

            // Check if the file is already attached (eFile duplicates)
            attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button&quot; , &quot;'&quot; , &quot;).each(function () {
                if ($(this).attr(&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;) === btnValue) {
                    found = true;
                    return false;
                }
            });

            // Also check local file attachments (added via data-file-name attribute)
            if (!found) {
                attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;button[data-file-name]&quot; , &quot;'&quot; , &quot;).each(function () {
                    var localFileName = $(this).attr(&quot; , &quot;'&quot; , &quot;data-file-name&quot; , &quot;'&quot; , &quot;);
                    if (localFileName === btnValue) {
                        found = true;
                        return false; // break the loop
                    }
                });
            }

            // Also check forwarded attachments (buttons inside span elements with data-file-name)- foward case 
            if (!found) {
                attachmentsContainer.find(&quot; , &quot;'&quot; , &quot;span button[data-file-name]&quot; , &quot;'&quot; , &quot;).each(function () {
                    var forwardedFileName = $(this).attr(&quot; , &quot;'&quot; , &quot;data-file-name&quot; , &quot;'&quot; , &quot;);
                    if (forwardedFileName === btnValue) {
                        found = true;
                        return false; // break the loop
                    }
                });
            }

            if (found) {
                showWarningBar(&quot;This e-file has already been shared&quot;);
                continue;
            }

            var fileName = file.FriendlyName.trim();
            var fileExtension = &quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;;

            // Check if file name exceeds character limit
            if (fileName.length > 15) {
                fileExtension = fileName.split(&quot; , &quot;'&quot; , &quot;.&quot; , &quot;'&quot; , &quot;).pop();
                fileName = fileName.substring(0, 15 - fileExtension.length - 1).trim() + &quot; , &quot;'&quot; , &quot;.&quot; , &quot;'&quot; , &quot; + fileExtension;
            }

            // Check if file size is 0 bytes
            if (file.FileSize === 0 || file.FileSize === undefined || file.FileSize === null) {
                showWarningBar(&quot; , &quot;'&quot; , &quot;Attached files must be greater than 0 bytes&quot; , &quot;'&quot; , &quot;);
                stopBusyIndicator();
                return;
            }

            // Check total file size including new file
            totalSize += file.FileSize;
            if (totalSize > 26214400) {
                showWarningBar(&quot;The attachment size exceeds the allowable limit. Maximum size of all attachments allowed is 25 MB.&quot;);
                stopBusyIndicator();
                return;
            }

            // Check file format
            var allowedFormats = [&quot; , &quot;'&quot; , &quot;jpg&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;pdf&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;doc&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;png&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;txt&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;xml&quot; , &quot;'&quot; , &quot;];
            var fileExtension = file.Extension.toLowerCase();

            if (allowedFormats.indexOf(fileExtension) === -1) {
                newShowConfirmationBarWithCustomButton(&quot; , &quot;'&quot; , &quot;warringPopup&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;Unsupported file format: &quot; , &quot;'&quot; , &quot; + fileExtension, btnobj, &quot;width50&quot;, true);
                stopBusyIndicator();
                return;
            }

            // Create attachment button
            var button = $(&quot; , &quot;'&quot; , &quot;&lt;button class=&quot;font16 marR10&quot; value=&quot;&quot; , &quot;'&quot; , &quot; + btnValue + &quot; , &quot;'&quot; , &quot;&quot; data-file=&quot;&quot; , &quot;'&quot; , &quot; + file.sToBase64StringOfEfile + &quot; , &quot;'&quot; , &quot;&quot; data-file-size=&quot;&quot; , &quot;'&quot; , &quot; + file.FileSize + &quot; , &quot;'&quot; , &quot;&quot;>&quot; , &quot;'&quot; , &quot; + fileName + &quot; , &quot;'&quot; , &quot;&amp;nbsp;&amp;nbsp;&lt;span class=&quot;mif-delete font18 marL06 marB05&quot;>&lt;/span>&lt;/button>&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;#Compose-no-attachments&quot; , &quot;'&quot; , &quot;).hide();
            attachmentsContainer.append(button);
        }
    }




    function PreviewEfileForPP(RecordGuid, Source, FileId, FileName) {
        var patid = $(&quot; , &quot;'&quot; , &quot;#hiddenPatId&quot; , &quot;'&quot; , &quot;).val();
        var param = {};
        param[&quot; , &quot;'&quot; , &quot;Id&quot; , &quot;'&quot; , &quot;] = patid;
        param[&quot; , &quot;'&quot; , &quot;RecordGuid&quot; , &quot;'&quot; , &quot;] = RecordGuid;
        param[&quot; , &quot;'&quot; , &quot;Source&quot; , &quot;'&quot; , &quot;] = Source;
        param[&quot;FileAction&quot;] = &quot;Edit&quot;;
        param[&quot;IsEfilePreview&quot;] = true; //if file preview then true and it get single file from DB

        param[&quot;LstEFile&quot;] = [];
        var fileName = FileName;
        var fileNameExtObj = Efile_GetFileName_FileExtenstion_for_PP(fileName);

        param[&quot;LstEFile&quot;][0] = {};
        param[&quot;LstEFile&quot;][0][&quot;Name&quot;] = FileId;
        if (fileNameExtObj != null) {
            param[&quot;LstEFile&quot;][0][&quot;FriendlyName&quot;] = fileNameExtObj.fileName;
            param[&quot;LstEFile&quot;][0][&quot;Extension&quot;] = fileNameExtObj.fileExt;
        }
        else {
            param[&quot;LstEFile&quot;][0][&quot;FriendlyName&quot;] = &quot;&quot;;
            param[&quot;LstEFile&quot;][0][&quot;Extension&quot;] = &quot;&quot;;
        }
        ShowModalPopup(&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;popup-90 grad-gray-white&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;AddEditFileMetadata/FileManager&quot; , &quot;'&quot; , &quot;, param, null);
    }

    function Efile_GetFileName_FileExtenstion_for_PP(fileName) {
        var extIndex = fileName.lastIndexOf(&quot; , &quot;'&quot; , &quot;.&quot; , &quot;'&quot; , &quot;);
        var fExt = null;

        if (extIndex != -1) {
            fExt = fileName.substring(extIndex + 1, fileName.length)
            fileName = fileName.substring(0, extIndex);

            return { fileName: fileName, fileExt: fExt.toLowerCase() };
        }
        return null;
    }


            &quot;))]</value>
      <webElementGuid>b883956f-e136-43dc-9bbb-3042df5bf1ff</webElementGuid>
   </webElementXpaths>
</WebElementEntity>
