<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>div_encounterform13ea63070d</name>
   <tag></tag>
   <elementGuidId>a98d6347-dc69-4287-bc2e-43c147cd7b32</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value>//*[(@aria-labelledby = 'encTabList_2') and @class and contains(concat(' ', normalize-space(@class), ' '), ' enContentHt ')]</value>
      </entry>
      <entry>
         <key>CSS</key>
         <value>[aria-labelledby=&quot;encTabList_2&quot;].enContentHt</value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <smartLocatorCollection>
      <entry>
         <key>SMART_LOCATOR</key>
         <value>internal:role=tabpanel[name=&quot;Final Findings&quot;i]</value>
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
      <webElementGuid>e55ec38a-8592-495b-aa8a-13b9c1036a7b</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>class</name>
      <type>Main</type>
      <value>enContentHt customizedFont ui-tabs-panel ui-widget-content ui-corner-bottom</value>
      <webElementGuid>6bc31a84-de3c-414c-9d5a-2d360a44c305</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>id</name>
      <type>Main</type>
      <value>encounterform13ea63070d</value>
      <webElementGuid>d8c4bea5-83d7-46f2-a332-7d2a4d44dd1c</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>data-recordguidform</name>
      <type>Main</type>
      <value>ea63070d</value>
      <webElementGuid>74dd3e7d-e5c5-4c34-8734-4e998628b810</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>data-pagerid</name>
      <type>Main</type>
      <value>368</value>
      <webElementGuid>dd5042fd-4714-40da-9145-fb9988def1f1</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>aria-labelledby</name>
      <type>Main</type>
      <value>encTabList_2</value>
      <webElementGuid>3169fc5a-ca63-4469-8d81-c43a7cf12334</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>role</name>
      <type>Main</type>
      <value>tabpanel</value>
      <webElementGuid>6475d6c0-ed3e-47b2-b440-dc88e3033129</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>aria-hidden</name>
      <type>Main</type>
      <value>false</value>
      <webElementGuid>4b235b37-44e6-40d6-8fc6-157f0af79a65</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>Final Diagnoses#1AddAssessmentsPlansExclude from IPBaseline ExamAnnual exam
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
    });




    
    



    //0049539: Data Loss - Final RX data in Encounters and notes on Patient records disappeared
    var planDivLength = $('div#encounterPlansData').length;
    if (planDivLength > 1) {
        var formPageRelId = $('[id^=encTabList_]').closest('.active-toggle').parent().attr('data-formpagerelid');
        if (!checknullBlankUndefind(formPageRelId) &amp;&amp; checknullBlankUndefind($('#IsOnEncFormScreen').val())) {
            for (var i = 0; i &lt; planDivLength; i++) {
                var findClosestDivForId = $('div#encounterPlansData')[i].closest('[id^=encformbase]');
                if (!checknullBlankUndefind(findClosestDivForId)) {
                    var otherPageRelId = $(findClosestDivForId).attr('data-formpagerelid');
                    if (!checknullBlankUndefind(otherPageRelId) &amp;&amp; otherPageRelId != formPageRelId) {
                        var $finalDiagnosesDivLength = $(&quot;div[data-formpagerelid=&quot; + otherPageRelId + &quot;]&quot;).find(&quot;div[data-elementinternalname='&quot; + 'Final_Diagnoses' + &quot;']&quot;);
                        if ($finalDiagnosesDivLength.length > 0) {
                            $finalDiagnosesDivLength.remove();
                        }
                        break;
                    }
                }
            }
        }
    }
    $(document).ready(function () {
        if (GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;) != null &amp;&amp; GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;).PreferenceValue == &quot;Y&quot;) {
            $('textarea[name*=&quot;Final_Diagnoses.ASSESSMENT&quot;]').css('overflow', 'hidden');
            $('#encounterPlansData').css('overflow', 'hidden');
        }
        var planHt = $(&quot;div[data-ec_ctrldispname='PLAN_HTML']&quot;).height();
        $('#encounterPlansData').css('height', planHt + 'px');
        //Due to https://h00ghi19.maximeyes.com/ URL issue - data showing in new line so we changed below code
        var PlanLocalData = localStorage.getItem(&quot;PlanHTML&quot;);
        if (PlanLocalData != &quot;&quot; &amp;&amp; PlanLocalData != null) {
            var planHtmlData = $.parseHTML(PlanLocalData);
            checkLocaStorageforSetData(&quot;PlanHTML&quot;, &quot;&quot;);
        }
        else {
            var planHtmlData = ``; //MBT #35015, #35014 when plan data has &quot;`&quot; and &quot;'&quot;
        }

        if (planHtmlData != '') $('div[name*=PLAN_HTML]').find('div[id^=encounterPlansData]').html(planHtmlData).trigger('change');
        //$('textarea[name*=PLAN]').css(&quot;visibility&quot;, &quot;hidden&quot;);
        $('textarea[name*=PLAN][data-elementname=&quot;Final_Diagnoses&quot;]').css(&quot;visibility&quot;, &quot;hidden&quot;);
    });

    if (($(currentActivatedForm).attr('islocked') != undefined &amp;&amp; $(currentActivatedForm).attr('islocked').toLowerCase() == &quot;true&quot;)|| ($(currentActivatedForm).attr('iseditable') != undefined &amp;&amp; $(currentActivatedForm).attr('iseditable').toLowerCase() == &quot;false&quot;)) {
        $(&quot;#encounterPlansData&quot;).bind(&quot;keydown&quot;, function (e) {
            e.preventDefault();
            return;
        });
    }


    //function CheckEncounterLockStat(e) {
    //    if ($(currentActivatedForm).attr('islocked').toLowerCase() == &quot;true&quot;) {
    //       // showConfirmationBar(&quot;Encounter Locked!&quot;, &quot;The selected encounter is locked. Do you want to add an amendment for this encounter?&quot;, &quot;OK&quot;, &quot;Cancel&quot;, &quot;&quot;);
    //        e.textContent = &quot;&quot;;
    //       // $(&quot;#OK&quot;).unbind('click');
    //        //$(&quot;#OK&quot;).bind('click', function () {
    //        //    e.textContent = &quot;&quot;;
    //        //    ShowModalPopup('Patient Encounter Amendment', 'popup-30', 'GetAddendumNotes/PatientRecord', null, null);
    //        //});
    //        //e.textContent = &quot;&quot;;
    //        //$(&quot;#Cancel&quot;).unbind('Cancel');
    //        //$(&quot;#Cancel&quot;).bind('click', function () {
    //        //    e.textContent = &quot;&quot;;
    //        //});
    //    }
    //}

    $('#encounterPlansData').bind('blur', function () {
        var plansData = getPlanText(&quot;#encounterPlansData&quot;, true);
        plansData = $('&lt;div>' + plansData + '&lt;/div>')[0].textContent;
        $('textarea[name*=PLAN][data-elementname=&quot;Final_Diagnoses&quot;]').val(plansData).trigger('change');
        submitChanges(false);

        var planHtmlElement = $('div[name*=PLAN_HTML]');
        if (currentActivatedForm != false) {
            var asHashKey = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr('formrootid');
            asHashKey = asHashKey + &quot;~S~F_F~Final_Diagnoses.PLAN_HTML&quot;;
            var data = $.trim($('#encounterPlansData').html());
            trackChanges(asHashKey, data, planHtmlElement);
            submitChanges(false);
        }

        // Save Child Record
        savePlansChildRecord(&quot;#encounterPlansData&quot;);
    });


    /*div#encounterPlansData {
        overflow: auto;
    }*/

        div#encounterPlansData div {
            display: block;
            text-align: left !important;
        }

        div#encounterPlansData .Link {
            color: blue;
        }



        /*div#encounterPlansData input:hover{
             color: #005cb9;
         }*/

        div#encounterPlansData span {
            vertical-align: top;
            color: black;
        }




    $(document).ready(function () {
        var $encisExcludeFromMU = $(currentActivatedForm).find('input[type=hidden][id ^=IsExcludeFromMU_]');
        if ($encisExcludeFromMU.length > 0) {
            if ($(&quot;#encMainDiv&quot;).length > 0) return;
            var fd_isExcludefromMU = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]');
            if (fd_isExcludefromMU.length > 0) {
                var $encisExcludeFromMU = ($encisExcludeFromMU.val() == 'true' || $encisExcludeFromMU.val() == 'True') ? true : false;
                if ($encisExcludeFromMU &amp;&amp; typeof fd_isExcludefromMU[0] != &quot;undefined&quot; &amp;&amp; !fd_isExcludefromMU[0].checked) {
                    if (!fd_isExcludefromMU[0].checked) {
                        fd_isExcludefromMU[0].checked = true;
                        onSimpleControlDataChange(fd_isExcludefromMU);
                    }
                    //if (!$encisExcludeFromMU[0].checked) {
                    //    $encisExcludeFromMU[0].checked = true;
                    //    onSimpleControlDataChange($encisExcludeFromMU);
                    //}
                }
                else if (!$encisExcludeFromMU &amp;&amp; typeof fd_isExcludefromMU[0] != &quot;undefined&quot; &amp;&amp; fd_isExcludefromMU[0].checked) {
                    if (fd_isExcludefromMU[0].checked) {
                        fd_isExcludefromMU[0].checked = false;
                        onSimpleControlDataChange(fd_isExcludefromMU);
                    }
                }
            }
        }
        if ($(&quot;#IsExcludeFromMU&quot;)[0] != undefined &amp;&amp; $(&quot;#IsExcludeFromMU&quot;)[0].checked == true) {
            $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]')[0].checked = true;
        }
        else if ($(&quot;#IsExcludeFromMU&quot;)[0] != undefined &amp;&amp; $(&quot;#IsExcludeFromMU&quot;)[0].checked == false) {
            $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]')[0].checked = false;
        }

        if ($(currentActivatedForm).attr('patientencounterid') != undefined) {
            if ($(&quot;#encMainDiv&quot;).length > 0) return;
            var $encisBaselineExam = $(currentActivatedForm).find('input[type=hidden][id ^=IsBaseLineExam_]');
            if ($encisBaselineExam.length > 0) {
                var fd_isBaselineExam = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_BASELINE_EXAM]');
                var $isBaselineExam = ($encisBaselineExam.val() == 'true' || $encisBaselineExam.val() == 'True') ? true : false;
                if (fd_isBaselineExam) {
                    if (typeof fd_isBaselineExam[0] != &quot;undefined&quot; &amp;&amp; !fd_isBaselineExam[0].checked &amp;&amp; $encisBaselineExam.val() == 'True') {
                        fd_isBaselineExam[0].checked = true;
                        onSimpleControlDataChange(fd_isBaselineExam);
                    }
                }
            }

            var $encisAnnualExam = $(currentActivatedForm).find('input[type=hidden][id ^=IsAnnualExam_]');
            if ($encisAnnualExam.length > 0) {
                var fd_isAnnualExam = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ANNUAL_EXAM]');
                var $isAnnualexam = ($encisAnnualExam.val() == 'true' || $encisAnnualExam.val() == 'True') ? true : false;
                if (fd_isAnnualExam) {
                    if (($isAnnualexam) &amp;&amp; typeof fd_isAnnualExam[0] != &quot;undefined&quot; &amp;&amp; !fd_isAnnualExam[0].checked) {
                        fd_isAnnualExam[0].checked = true;
                        onSimpleControlDataChange(fd_isAnnualExam);
                    }
}


            }
            //}
            $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]').change(function (event) {
                onSimpleControlDataChange(this);
                var key = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr('formrootid') + &quot;~S&quot; + &quot;~IsExcludeFromMU&quot;;
                var fieldValue = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]')[0].checked;
                var $current = $('input[type=hidden][id^=IsExcludeFromMU]');
                $(&quot;#IsExcludeFromMU&quot;)[0].checked = fieldValue;
                trackChanges(key, fieldValue, $current);
                //onclick of IsExcludeFromIP checkbox from Final Diagnosis element,it should change the value from hamburger menu also.
                if (fieldValue == true) {
                   $(&quot;.excludeCheck&quot;).addClass(&quot;mif-checkMark&quot;);
                }
                else {
                   $(&quot;.excludeCheck&quot;).removeClass(&quot;mif-checkMark&quot;);
                }
                currentActivatedForm.attr('data-changed', true);
            });

            $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_BASELINE_EXAM]').change(function (event) {
                var fd_isAnnualExam = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ANNUAL_EXAM]');
                onSimpleControlDataChange(this);
                var key = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr('formrootid') + &quot;~S&quot; + &quot;~IsBaseLineExam&quot;;
                var fieldValue = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_BASELINE_EXAM]')[0].checked;
                var $current = $('input[type=hidden][id^=IsBaseLineExam]');
                trackChanges(key, fieldValue, $current);
                if (!checkValISNullUndefinedBlank(fieldValue))
                {
                    var $encFormBaselineExam = $(currentActivatedForm).find('input[type=hidden][id ^=IsBaseLineExam_]');
                    $encFormBaselineExam.val(fieldValue);
                }
                if (this.checked &amp;&amp; !fd_isAnnualExam[0].checked) {
                    //fd_isAnnualExam[0].checked = this.checked;
                    // onSimpleControlDataChange(fd_isAnnualExam);
                }

            });
            $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ANNUAL_EXAM]').change(function (event) {
                var fd_isBaselineExam = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_BASELINE_EXAM]');
                var fieldValue = $(&quot;div[data-elementinternalname='Final_Diagnoses']&quot;).find('input[name*=IS_ANNUAL_EXAM]')[0].checked;
                onSimpleControlDataChange(this);
                if (!checkValISNullUndefinedBlank(fieldValue)) {
                    var $encFormAnnualExam = $(currentActivatedForm).find('input[type=hidden][id ^=IsAnnualExam_]');
                    $encFormAnnualExam.val(fieldValue);
                }
                if (this.checked &amp;&amp; !fd_isBaselineExam[0].checked) {
                    //fd_isBaselineExam[0].checked = this.checked;
                    //onSimpleControlDataChange(fd_isBaselineExam);
                }
            });

        }
        if ('False' == 'True' || 'False' == 'true') {
            $('[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]').attr('IsBillCodeSeqChanged', 'true');
            $('[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]').show();
            $('[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]').powerTip({ placement: 'se', mouseOnToPopup: true }).data('powertip', function () {
                var r = $(&quot;&lt;div class='no-margin font14 pad05'>Diagnosis code order is changed, click refresh code on final procedure code&lt;/div>&quot;);
                return r;
            });
        } else {
            $('[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]').attr('IsBillCodeSeqChanged', 'false');
            $('[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]').hide();
        }

        plansSectionsForAnnualReason();
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });

    //var asHashKeyData;
    //var mainGridName;
    //var hash = {};

    //function ShowDEPopUp(gridName) {
    //    var asHashKey = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr('formrootid');
    //    var title = 'Final Diagnosis - Data Entry';
    //    var callbackUrl = 'FinalDiagnosisShowPopUp/ComplexElements';
    //    var popUpSize = 'popup-100';
    //    var patientEncounterId = currentActivatedForm.attr('patientEncounterId');
    //    asHashKeyData = asHashKey + $(gridName.mainElement).find('[name*=&quot;_GF_&quot;]').closest('div[data-sysdef]').attr('data-askey');
    //    mainGridName = gridName;
    //    hash = {};
    //    finalDiagonsisGridView_ShowPopUp(title, callbackUrl, popUpSize, gridName, patientEncounterId);
    //    uniqueElmInterName = {};
    //}




    
        
            
                
                        
                
                
                        
                
                
                        
                
                
                        

                
                
            
        
    
    

	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems('finalDiagnosesGridView_e65e_EEG_DXSE',[[['dxeButtonEditButtonHover_Metropolis'],[''],['B-100']]]);
ASPx.AddPressedItems('finalDiagnosesGridView_e65e_EEG_DXSE',[[['dxeButtonEditButtonPressed_Metropolis'],[''],['B-100']]]);
ASPx.AddDisabledItems('finalDiagnosesGridView_e65e_EEG_DXSE',[[['dxeDisabled_Metropolis'],[''],['','I']],[['dxeDisabled_Metropolis dxeButtonDisabled_Metropolis'],[''],['B-100'],,[[{'spriteCssClass':'dxEditors_edtClearDisabled_Metropolis'}]],['Img']]]);
ASPx.createControl(ASPxClientButtonEdit,'finalDiagnosesGridView_e65e_EEG_DXSE','',{'uniqueID':'finalDiagnosesGridView_e65e_EEG$DXSE','scStates':2,'scPostfix':'Metropolis','stateObject':{'rawValue':''},'nullText':'Search Existing Final Diagnoses','forceShowClearButtonAlways':true},null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''},{'key':'N','className':'dxeNullText_Metropolis','cssText':''}]});

//-->

				
			
		
			
				
					
						
					
						
							
								No. 
							
						
							
								Code 
							
						
							
								Description 
							
						
							
								Coding System 
							
						
							
								Actions 
							
						
							
								# 
							
						
					
				
			
		
			
				
					
				
					    
				
					
						No data to display
					
				
			
		
			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'DIAG_POINTER_GF_e65e_GF','',{'scStates':6,'scPostfix':'Metropolis'},{'Init':function(s,e){$(s.GetInputElement()).attr('data-nochangeevent','True');},'LostFocus':finalDiagnoses_LostFocus,'KeyDown':finalDiagnoses_KeyDown},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'DIAGNOSIS_CODE_GF_e65e_GF','',{'scStates':6,'scPostfix':'Metropolis'},{'Init':function(s,e){$(s.GetInputElement()).attr('data-nochangeevent','True');},'LostFocus':finalDiagnoses_LostFocus,'KeyDown':finalDiagnoses_KeyDown},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'DIAGNOSIS_CODE_DESCRIPTION_GF_e65e_GF','',{'scStates':6,'scPostfix':'Metropolis'},{'Init':function(s,e){$(s.GetInputElement()).attr('data-nochangeevent','True');},'LostFocus':finalDiagnoses_LostFocus,'KeyDown':finalDiagnoses_KeyDown},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'CODING_SYSTEM_GF_e65e_GF','',{'scStates':6,'scPostfix':'Metropolis'},{'Init':function(s,e){$(s.GetInputElement()).attr('data-nochangeevent','True');},'LostFocus':finalDiagnoses_LostFocus,'KeyDown':finalDiagnoses_KeyDown},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,'finalDiagnosesGridView_e65e_EEG','',{'callBack':function(arg) { ; },'stateObject':{'keys':[],'resizingState':'','callbackState':'guYO4+k0S5jwy1tF/QoXin9jKJ7n7ykTCy4dMZxMd9fHKtzpRbLNKZ8aOp40PYwzMa4SFvnfbU2DRs9T2IHy5y8fgk0byRib9oTe8rud6PgWdGkG4L5tYvTPiI/n2sTl1RMn+JwVUVPnzYxKqdV/5JOnBD9ezhljH3+8ppdkXPuHIy4yvvWCmo1ZLV32lN/422XFEjtsCynatWQz2Pju6sz7lHInsaOlkMks/HiGUzMzXA2UhS2pkdIeAHSbCR/dVTSEKCUaDsAzkkb7uHL+Q/3jmDGHNrAZNaNoOSxOA24PyffcnwLLudTnDegaijm7hU+IkGQQzTxvDX4bj3Bjfh5aTDpcejqWSvZuq0IW06zqeu0jf1YOjkoP5tM6Xz0SR9awR3aq6Ty1zmV5OuwiRJ4pqjCi1XiG598ibt3TbZfuKtj11dA00r9eqMumQJg/50B/tqy2/89DDVMxLki7tSt+iILg0j6Gnbv8Vgo4q+eO9dFK+wNmcSjNK5C/Trza48jhtHyW9bFPqox93045PUWJm/k=','groupLevelState':{},'scrollState':null,'selection':'','batchEditClientModifiedValues':{}},'cp_DIAG_POINTER':'No.','cp_DIAGNOSIS_CODE':'Code','cp_DIAGNOSIS_CODE_DESCRIPTION':'Description','cp_CODING_SYSTEM':'Coding System','cp_':'Actions','cp_gridRowData':{},'cp_gridData':{},'cp_digcode_ptrs':['RowGuid','RecordId','DIAG_POINTER','DIAGNOSIS_CODE','DIAGNOSIS_CODE_DESCRIPTION','CODING_SYSTEM'],'callBacksEnabled':true,'pageRowCount':0,'pageRowSize':10,'pageIndex':-1,'pageCount':0,'selectedWithoutPageRowCount':0,'checkBoxImageProperties':{'4':['dxWeb_edtCheckBoxChecked_Metropolis','dxWeb_edtCheckBoxUnchecked_Metropolis','dxWeb_edtCheckBoxGrayed_Metropolis'],'8':['dxWeb_edtCheckBoxCheckedDisabled_Metropolis','dxWeb_edtCheckBoxUncheckedDisabled_Metropolis','dxWeb_edtCheckBoxGrayedDisabled_Metropolis']},'icbFocusedStyle':['dxICBFocused_Metropolis',''],'visibleStartIndex':0,'focusedRowIndex':-1,'allowFocusedRow':false,'allowSelectByItemClick':false,'allowSelectSingleRowOnly':false,'vertScroll':2,'callbackOnFocusedRowChanged':false,'callbackOnSelectionChanged':false,'editState':0,'editItemVisibleIndex':-1,'allowBatchEditing':true,'batchEditClientState':{'binaryImageColumnsDisplayHtml':{},'hiddenEditorColumnIndices':[],'validationInfo':null,'editColumnIndices':[1,2,3,4,5],'startEditAction':0,'comboBoxCallbackModeItemsInfo':{},'preventUpdateCellTextForDataItemTemplate':0,'comboBoxColumnsDisplayHtml':{},'ellipsisColumns':[],'templateColumnIndices':[1,2,3,4,5],'isNewRowOnTop':true,'columnsNullDisplayTextInfo':{},'validateOnEndEdit':1,'dataItemTemplateColumnIndices':[5],'colorColumnsDisplayHtml':{},'editMode':1,'allowEndEditOnError':1,'progressBarColumnIndices':[],'comboBoxColumnsWithServerEventsIndices':[],'updateInfo':{},'highlightDeletedItems':false,'nonEditableColumnValues':{},'checkColumnsDisplayHtml':{}},'batchEditPageValues':{'NIV':{'1':null,'2':null,'3':null,'4':null,'5':null}},'searchPanelFilter':'','allowFocusedCell':true,'allowDelete':true,'allowEdit':true,'allowInsert':true,'columnProp':[[0,,,,0,,,,100,,1,,,0],[1,,,'DIAG_POINTER',0,,,,0],[2,,,'DIAGNOSIS_CODE',0,,,,1],[3,,,'DIAGNOSIS_CODE_DESCRIPTION',0,,,,2],[4,,,'CODING_SYSTEM',0,,,,3],[5,,'No_Focus',,0,,,,4,,,,,0]],'editMode':4,'indentColumnCount':0,'allowChangeColumnHierarchy':false,'allowMultiColumnAutoFilter':false,'columnResizeMode':2,'editingItemVisibleIndex':-1,'callbackUrl':'/ComplexElements/FinalDiagnosesGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=e65e','keyName':'RowGuid'},{'Init':finalDiagnosesGridView_Init,'BeginCallback':enc_grid_before_callback,'EndCallback':enc_grid_end_callback,'BatchEditStartEditing':finalDiagnosesGridView_BatchEditStartEditing,'BatchEditEndEditing':gridView_BatchEditEndEditing,'FocusedCellChanging':onFocusedCellChanging,'BatchEditRowValidating':finalDiagnosesGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize('finalDiagnosesGridView_e65e_EEG',({'commandButtonIDs':[],'styleInfo':{'ei':'&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;6&quot;>&lt;/td>\r\n&lt;/tr>','fc':{'css':'dxgvFocusedCell_Metropolis'},'bec':{'css':'dxgvBatchEditCell_Metropolis dxgv'},'bemc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgv'},'bemergmc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv'},'sel':{'css':'dxgvSelectedRow_Metropolis'},'fi':{'css':'dxgvFocusedRow_Metropolis'},'fgi':{'css':'dxgvFocusedGroupRow_Metropolis'}}}));

//-->




    $(document).ready(function () {
        var rows = [];
            var rowKeys = Object.keys(rows)
            for (var r = 0; r &lt; rowKeys.length; r++) {
                complexElementOldData[rows[r].RowGuid] = rows[rowKeys[r]];
            }
            const $editableDiv = $('#encounterPlansData');
            $('#encounterPlansData').attr('orignal-height',$('#encounterPlansData').css('height'))
            function adjustHeightFromElement() {
                $editableDiv.css('height', 'auto');
                if($editableDiv[0].scrollHeight &lt; parseInt($('#encounterPlansData').attr('orignal-height'), 10)){
                    $editableDiv.css('height', $('#encounterPlansData').attr('orignal-height'))
                }
                else
                $editableDiv.css('height', $editableDiv[0].scrollHeight + 'px'); // Set new height based on content
            }

            if (GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;) != null &amp;&amp; GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;).PreferenceValue == &quot;Y&quot;) {
                $editableDiv.on('focus', function () {
                    $(this).addClass('expanded');
                    adjustHeightFromElement();
                });
                $editableDiv.on('blur', function () {
                    $(this).removeClass('expanded');
                    $(this).css('height', $('#encounterPlansData').attr('orignal-height')); // Reset to original height
                });
                $editableDiv.on('input', function () {
                    adjustHeightFromElement();
                });
            }
    });
   

    

Final Procedures#1Add
    $(document).ready(function () {
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });





    
        
            
                
                    Select Code Bundle
EP Routine Exam + Refract + Optos
Glaucoma 99213 + Pachy,  VF, OCT of Optic nerve
NP Routine Exam + OPT + VF
NP Routine Exam + Refract + Optos
NP Routine Exam + Refract + Optos(on 2nd bill)

                
                
                    
                

                
                        
                
                
                            
                
                
                
                        

                
                
            
        
    

    


	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems('finalProceduresGridView_de8b_EEG_DXSE',[[['dxeButtonEditButtonHover_Metropolis'],[''],['B-100']]]);
ASPx.AddPressedItems('finalProceduresGridView_de8b_EEG_DXSE',[[['dxeButtonEditButtonPressed_Metropolis'],[''],['B-100']]]);
ASPx.AddDisabledItems('finalProceduresGridView_de8b_EEG_DXSE',[[['dxeDisabled_Metropolis'],[''],['','I']],[['dxeDisabled_Metropolis dxeButtonDisabled_Metropolis'],[''],['B-100'],,[[{'spriteCssClass':'dxEditors_edtClearDisabled_Metropolis'}]],['Img']]]);
ASPx.createControl(ASPxClientButtonEdit,'finalProceduresGridView_de8b_EEG_DXSE','',{'uniqueID':'finalProceduresGridView_de8b_EEG$DXSE','scStates':2,'scPostfix':'Metropolis','stateObject':{'rawValue':''},'nullText':'Search Existing Final Procedures','forceShowClearButtonAlways':true},null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''},{'key':'N','className':'dxeNullText_Metropolis','cssText':''}]});

//-->

				
			
		
			
				
					
						
					
						
							
								Bill To 
							
						
							
								Shortcut 
							
						
							
								Code 
							
						
							
								Description 
							
						
							
								Modifiers 
							
						
							
								Units 
							
						
							
								Diag. Ptrs. 
							
						
							
								Actions 
							
						
							
								# 
							
						
					
				
			
		
			
				
					
				
					       
				
					
						No data to display
					
				
			
		
			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'BILL_TO_GF_de8b_GF','',null,null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'SHORTCUT_GF_de8b_GF','',null,null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'PROCEDURE_CODE_GF_de8b_GF','',null,null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'PROCEDURE_CODE_DESCRIPTION_GF_de8b_GF','',null,null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'MODIFIERS_GF_de8b_GF','',null,null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'DX_POINTERS_GF_de8b_GF','',null,null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,'finalProceduresGridView_de8b_EEG','',{'callBack':function(arg) { ; },'stateObject':{'keys':[],'resizingState':'','callbackState':'NRqHpu6AS8T0kAvz3EscewJfHG4WMYCx69BlQqwshDh4+XIrVMx6Kpoc6FEQHE03hEblUQpdKGLpHyz9waPHkYRrcVU/CWd66hraFJl7yAN6T3rFQwqmgqqa4Jq/u4Dejy0lgbNGVOrIvhrGq9GdkcDapqPBziAfXD6e7fiUcpFwudBw9XTWyJCNg4rszYIZ+bTQlK9lPABJoxd3jS1HqWeOUIwXvbrgaHN2NIoe8pogKqGclnrK8Mzzup67EanROePDn0PmGVQsEDMyjm50DwWUgRUyIJxNKOiCKjKKv/ox7CL2AVqUbKl0WFuFX4z/GlQd1Dhc5NTFxZqBJ3Y11MctEK7lss3aGqXmuoyck3oSwWhP/PXUSjuibwiJ+yAWhfvg2nTFqA1J7OtTIJV6S7SpTP6Tz5DfEYMUW7YpQD4x46hM9PqG392LkdUS6XOFlXvwaIfC4uXt0i4BOnPfjn+39xTxWJEMmd/3gV2fDJVYWQy188AuvnRSYa99n3HFWJKy7D1ue+uEuS82uQJYK+Tg2tWGzK8m7s26lY/eXQu26/HJ15Jjsp3o9Qq82w64Fu3AeR1z9a7w4UygDCrhYy6NIqsfXyIaB4FsXVG0AGS+FQhU','groupLevelState':{},'scrollState':null,'selection':'','batchEditClientModifiedValues':{}},'cp_BILL_TO':'Bill To','cp_SHORTCUT':'Shortcut','cp_PROCEDURE_CODE':'Code','cp_PROCEDURE_CODE_DESCRIPTION':'Description','cp_MODIFIERS':'Modifiers','cp_UNITS':'Units','cp_DX_POINTERS':'Diag. Ptrs.','cp_':'Actions','cp_gridRowData':{},'cp_gridData':{},'callBacksEnabled':true,'pageRowCount':0,'pageRowSize':10,'pageIndex':-1,'pageCount':0,'selectedWithoutPageRowCount':0,'checkBoxImageProperties':{'4':['dxWeb_edtCheckBoxChecked_Metropolis','dxWeb_edtCheckBoxUnchecked_Metropolis','dxWeb_edtCheckBoxGrayed_Metropolis'],'8':['dxWeb_edtCheckBoxCheckedDisabled_Metropolis','dxWeb_edtCheckBoxUncheckedDisabled_Metropolis','dxWeb_edtCheckBoxGrayedDisabled_Metropolis']},'icbFocusedStyle':['dxICBFocused_Metropolis',''],'visibleStartIndex':0,'focusedRowIndex':-1,'allowFocusedRow':false,'allowSelectByItemClick':false,'allowSelectSingleRowOnly':false,'vertScroll':2,'callbackOnFocusedRowChanged':false,'callbackOnSelectionChanged':false,'editState':0,'editItemVisibleIndex':-1,'allowBatchEditing':true,'batchEditClientState':{'binaryImageColumnsDisplayHtml':{},'hiddenEditorColumnIndices':[],'validationInfo':null,'editColumnIndices':[1,2,3,4,5,6,7,8],'startEditAction':0,'comboBoxCallbackModeItemsInfo':{},'preventUpdateCellTextForDataItemTemplate':0,'comboBoxColumnsDisplayHtml':{},'ellipsisColumns':[],'templateColumnIndices':[1,2,3,4,5,6,7,8],'isNewRowOnTop':true,'columnsNullDisplayTextInfo':{},'validateOnEndEdit':1,'dataItemTemplateColumnIndices':[8],'colorColumnsDisplayHtml':{},'editMode':1,'allowEndEditOnError':1,'progressBarColumnIndices':[],'comboBoxColumnsWithServerEventsIndices':[],'updateInfo':{},'highlightDeletedItems':false,'nonEditableColumnValues':{},'checkColumnsDisplayHtml':{}},'batchEditPageValues':{'NIV':{'1':null,'2':null,'3':null,'4':null,'5':null,'6':null,'7':null,'8':null}},'searchPanelFilter':'','allowFocusedCell':true,'allowDelete':true,'allowEdit':true,'allowInsert':true,'columnProp':[[0,,,,0,,,,100,,1,,,0],[1,,,'BILL_TO',0,,,,0],[2,,,'SHORTCUT',0,,,,1],[3,,,'PROCEDURE_CODE',0,,,,2],[4,,,'PROCEDURE_CODE_DESCRIPTION',0,,,,3],[5,,,'MODIFIERS',0,,,,4],[6,,,'UNITS',0,,,,5],[7,,,'DX_POINTERS',0,,,,6],[8,,'No_Focus',,0,,,,7,,,,,0]],'editMode':4,'indentColumnCount':0,'allowChangeColumnHierarchy':false,'allowMultiColumnAutoFilter':false,'columnResizeMode':2,'editingItemVisibleIndex':-1,'callbackUrl':'/ComplexElements/FinalProceduresGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=de8b','keyName':'RowGuid'},{'Init':finalProceduresGridView_Init,'BeginCallback':enc_grid_before_callback,'EndCallback':enc_grid_end_callback,'BatchEditStartEditing':finalProceduresGridView_BatchEditStartEditing,'BatchEditEndEditing':gridView_BatchEditEndEditing,'FocusedCellChanging':onFocusedCellChanging,'BatchEditRowValidating':finalProceduresGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize('finalProceduresGridView_de8b_EEG',({'commandButtonIDs':[],'styleInfo':{'ei':'&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;9&quot;>&lt;/td>\r\n&lt;/tr>','fc':{'css':'dxgvFocusedCell_Metropolis'},'bec':{'css':'dxgvBatchEditCell_Metropolis dxgv'},'bemc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgv'},'bemergmc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv'},'sel':{'css':'dxgvSelectedRow_Metropolis'},'fi':{'css':'dxgvFocusedRow_Metropolis'},'fgi':{'css':'dxgvFocusedGroupRow_Metropolis'}}}));

//-->

    


    .dxgvSearchPanel_Metropolis.Search_Final_Procedures table > tbody > tr > td:first-child > table {
    width: 73% !important;
}
Final Outbound Documents#1Add    
    $(document).ready(function () {
        setMaxlengthAttribute(true);
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });
  


    
        
            
                
                        
                
                
                    
                
                
                    
                
                
                    
                
                
                        
                
                
                        
                
                
                        
                
                
            
        
    
    

	
		
	
		
			
				
					
				
			
&lt;!--
ASPx.AddHoverItems('finalOutboundDocumentGridView_d762_EEG_DXSE',[[['dxeButtonEditButtonHover_Metropolis'],[''],['B-100']]]);
ASPx.AddPressedItems('finalOutboundDocumentGridView_d762_EEG_DXSE',[[['dxeButtonEditButtonPressed_Metropolis'],[''],['B-100']]]);
ASPx.AddDisabledItems('finalOutboundDocumentGridView_d762_EEG_DXSE',[[['dxeDisabled_Metropolis'],[''],['','I']],[['dxeDisabled_Metropolis dxeButtonDisabled_Metropolis'],[''],['B-100'],,[[{'spriteCssClass':'dxEditors_edtClearDisabled_Metropolis'}]],['Img']]]);
ASPx.createControl(ASPxClientButtonEdit,'finalOutboundDocumentGridView_d762_EEG_DXSE','',{'uniqueID':'finalOutboundDocumentGridView_d762_EEG$DXSE','scStates':2,'scPostfix':'Metropolis','stateObject':{'rawValue':''},'nullText':'Search','forceShowClearButtonAlways':true},null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''},{'key':'N','className':'dxeNullText_Metropolis','cssText':''}]});

//-->

		
	

	
		
			
				
			
				
					
						Document 
					
				
					
						Recipients 
					
				
					
						Needs Review? 
					
				
					
						Reviewed? 
					
				
					
						Status 
					
				
					
						  
					
				
					
						
					
				
			
		
	

	
		
			
		
			No
		
			Cataract ConsultationOwvkmi L...NoSent
		
			
				No data to display
			
		
	

	

	
		
			
		
	

&lt;!--
var dxo = ASPx.GetControlCollection().Get('finalOutboundDocumentGridView_d762_EEG');
dxo.SetProperties({'callBacksEnabled':true,'pageRowCount':1,'pageRowSize':10,'pageIndex':-1,'pageCount':1,'selectedWithoutPageRowCount':0,'checkBoxImageProperties':{'4':['dxWeb_edtCheckBoxChecked_Metropolis','dxWeb_edtCheckBoxUnchecked_Metropolis','dxWeb_edtCheckBoxGrayed_Metropolis'],'8':['dxWeb_edtCheckBoxCheckedDisabled_Metropolis','dxWeb_edtCheckBoxUncheckedDisabled_Metropolis','dxWeb_edtCheckBoxGrayedDisabled_Metropolis']},'icbFocusedStyle':['dxICBFocused_Metropolis',''],'visibleStartIndex':0,'focusedRowIndex':-1,'allowFocusedRow':true,'allowSelectByItemClick':false,'allowSelectSingleRowOnly':false,'vertScroll':2,'callbackOnFocusedRowChanged':false,'callbackOnSelectionChanged':false,'editState':0,'editItemVisibleIndex':-1,'allowBatchEditing':true,'batchEditClientState':{'binaryImageColumnsDisplayHtml':{},'hiddenEditorColumnIndices':[],'validationInfo':null,'editColumnIndices':[6],'startEditAction':0,'comboBoxCallbackModeItemsInfo':{},'preventUpdateCellTextForDataItemTemplate':0,'comboBoxColumnsDisplayHtml':{},'ellipsisColumns':[],'templateColumnIndices':[6],'isNewRowOnTop':true,'columnsNullDisplayTextInfo':{},'validateOnEndEdit':1,'dataItemTemplateColumnIndices':[6],'colorColumnsDisplayHtml':{},'editMode':1,'allowEndEditOnError':1,'progressBarColumnIndices':[],'comboBoxColumnsWithServerEventsIndices':[],'updateInfo':{},'highlightDeletedItems':false,'nonEditableColumnValues':{'1d3962c1-4563-4d42-b32e-eedec39611de':{}},'checkColumnsDisplayHtml':{}},'batchEditPageValues':{'1d3962c1-4563-4d42-b32e-eedec39611de':{'6':null},'NIV':{'6':null}},'searchPanelFilter':'','selectAllBtnStateWithoutPage':null,'selectAllSettings':[{'index':0,'mode':1}],'allowFocusedCell':true,'allowDelete':true,'allowEdit':true,'allowInsert':true,'columnProp':[[0,,,,0,,,,100,,1,,,0],[1,,,'DOCUMENT_NAME',,,,,0],[2,,,'RECIPIENTS',,,,,1],[3,,,'NEEDS_REVIEW',,,,,2],[4,,,'IS_REVIEWED',,,,,3],[5,,,'STATUS',,,,,4],[6,,,,0,,,,5,,,,,0],[7,0,,'DOCUMENT_TYPE',,,,,,,,,,,,,1]],'editMode':4,'indentColumnCount':0,'allowChangeColumnHierarchy':false,'allowMultiColumnAutoFilter':false,'columnResizeMode':2,'editingItemVisibleIndex':-1});

//-->

&lt;!--
ASPxClientGridBase.PostponeInitialize('finalOutboundDocumentGridView_d762_EEG',({'commandButtonIDs':[],'styleInfo':{'ei':'&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>','fc':{'css':'dxgvFocusedCell_Metropolis'},'bec':{'css':'dxgvBatchEditCell_Metropolis dxgv'},'bemc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgv'},'bemergmc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv'},'sel':{'css':'dxgvSelectedRow_Metropolis'},'fi':{'css':'dxgvFocusedRow_Metropolis EmptyFocusedRow'},'fgi':{'css':'dxgvFocusedGroupRow_Metropolis'}}}));

//-->

	

&lt;!--
ASPx.createControl(MVCxClientGridView,'finalOutboundDocumentGridView_d762_EEG','',{'callBack':function(arg) { ; },'stateObject':{'focusedRow':'','keys':['6aee8f53-c6f4-4b90-8f17-ad18f9d69847'],'resizingState':'','callbackState':'gtQ5mScUQa42i15C4I6ODTbJhK7cGLWw2bDTsA1JZwnnJjHFTsx/t62d5iELNbjcmOAusn/Tx6HnJGT6zVnFS+AiQeMKsKjY4fs/kbs8N/x4aYRBa1yt60qi4301RagldAulQCOdIBe/TMr7y6Cs6oE8hqQ2IhjAfIcAhllDHn9FF4DVtZtJ2hfD0W9tOBRf5EWY9U7O91AQWasJj7YmzNydxialp2lXbzjDr8y1E4qK8YqB//qFicuGshMXVX7pU5N5G2z9B3YwN7R/EUH58T1iy7BC0MDDfO3CTShV4heQmzLB59/B94/lJCHvMxCr87BRCu/GZB8bzpjEjfwW4SqxVKg7T9Lvs0lPumZbmUJUjd6APGqrDdA2g2iNrDaHsG5WoL+S+3kfmdBHVhkywMeCeEpzHGnA2kHEbSAxZYdDPyFDEC8g3B7G7k3bj+7SNkrV+WD2lxSeZ2mU0unFhPUbzMk4Tw8BGWxfPa8qpNzwjHl+rUw6xogMI0/C3n3f/SsPESyJXLjY6mMQOxduu2p/ib8i0JQ2DkKugza4fge5TYgQoGHQwZaAsfqNpRskQfqgOLG45aSsWGAT6YV/LmYAgb8=','groupLevelState':{},'scrollState':null,'selection':'','batchEditClientModifiedValues':{}},'cp_DOCUMENT_NAME':'Document','cp_RECIPIENTS':'Recipients','cp_NEEDS_REVIEW':'Needs Review?','cp_IS_REVIEWED':'Reviewed?','cp_STATUS':'Status','cp_':'','cp_DOCUMENT_TYPE':'DOCUMENT_TYPE','cp_gridRowData':{'6aee8f53-c6f4-4b90-8f17-ad18f9d69847':{'RowGuid':'6aee8f53-c6f4-4b90-8f17-ad18f9d69847','RecordId':'195','STATUS':'Not Sent','DOCUMENT_NAME':'Complete Eye Exam Report Brief','RECIPIENTS':'Owvkmi Lomjftuh(Patient)','NEEDS_REVIEW':'False','IS_REVIEWED':'False'}},'cp_gridData':{'6aee8f53-c6f4-4b90-8f17-ad18f9d69847':'195'},'callBacksEnabled':true,'pageRowCount':1,'pageRowSize':10,'pageIndex':-1,'pageCount':1,'selectedWithoutPageRowCount':0,'checkBoxImageProperties':{'4':['dxWeb_edtCheckBoxChecked_Metropolis','dxWeb_edtCheckBoxUnchecked_Metropolis','dxWeb_edtCheckBoxGrayed_Metropolis'],'8':['dxWeb_edtCheckBoxCheckedDisabled_Metropolis','dxWeb_edtCheckBoxUncheckedDisabled_Metropolis','dxWeb_edtCheckBoxGrayedDisabled_Metropolis']},'icbFocusedStyle':['dxICBFocused_Metropolis',''],'visibleStartIndex':0,'focusedRowIndex':-1,'allowFocusedRow':true,'allowSelectByItemClick':false,'allowSelectSingleRowOnly':false,'vertScroll':2,'callbackOnFocusedRowChanged':false,'callbackOnSelectionChanged':false,'editState':0,'editItemVisibleIndex':-1,'allowBatchEditing':true,'batchEditClientState':{'binaryImageColumnsDisplayHtml':{},'hiddenEditorColumnIndices':[],'validationInfo':null,'editColumnIndices':[6],'startEditAction':0,'comboBoxCallbackModeItemsInfo':{},'preventUpdateCellTextForDataItemTemplate':0,'comboBoxColumnsDisplayHtml':{},'ellipsisColumns':[],'templateColumnIndices':[6],'isNewRowOnTop':true,'columnsNullDisplayTextInfo':{},'validateOnEndEdit':1,'dataItemTemplateColumnIndices':[6],'colorColumnsDisplayHtml':{},'editMode':1,'allowEndEditOnError':1,'progressBarColumnIndices':[],'comboBoxColumnsWithServerEventsIndices':[],'updateInfo':{},'highlightDeletedItems':false,'nonEditableColumnValues':{'6aee8f53-c6f4-4b90-8f17-ad18f9d69847':{}},'checkColumnsDisplayHtml':{}},'batchEditPageValues':{'6aee8f53-c6f4-4b90-8f17-ad18f9d69847':{'6':null},'NIV':{'6':null}},'searchPanelFilter':'','selectAllBtnStateWithoutPage':null,'selectAllSettings':[{'index':0,'mode':1}],'allowFocusedCell':true,'allowDelete':true,'allowEdit':true,'allowInsert':true,'columnProp':[[0,,,,0,,,,100,,1,,,0],[1,,,'DOCUMENT_NAME',,,,,0],[2,,,'RECIPIENTS',,,,,1],[3,,,'NEEDS_REVIEW',,,,,2],[4,,,'IS_REVIEWED',,,,,3],[5,,,'STATUS',,,,,4],[6,,,,0,,,,5,,,,,0],[7,0,,'DOCUMENT_TYPE',,,,,,,,,,,,,1]],'pendingEvents':['RaiseFocusedItemChangedOutOfServer'],'editMode':4,'indentColumnCount':0,'allowChangeColumnHierarchy':false,'allowMultiColumnAutoFilter':false,'columnResizeMode':2,'editingItemVisibleIndex':-1,'callbackUrl':'/ComplexElements/FinalOutboundDocumentGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=d762','keyName':'RowGuid'},{'Init':finalOutboundDocumentgridView_Init,'BeginCallback':enc_grid_before_callback,'EndCallback':enc_grid_end_callback,'SelectionChanged':onCheckChangeFOD,'BatchEditStartEditing':gridViewFOD_BatchEditStartEditing,'BatchEditEndEditing':gridView_BatchEditEndEditing,'BatchEditRowValidating':finalOutboundDocumentgridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize('finalOutboundDocumentGridView_d762_EEG',({'commandButtonIDs':[],'styleInfo':{'ei':'&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>','fc':{'css':'dxgvFocusedCell_Metropolis'},'bec':{'css':'dxgvBatchEditCell_Metropolis dxgv'},'bemc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgv'},'bemergmc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv'},'sel':{'css':'dxgvSelectedRow_Metropolis'},'fi':{'css':'dxgvFocusedRow_Metropolis EmptyFocusedRow'},'fgi':{'css':'dxgvFocusedGroupRow_Metropolis'}}}));

//-->


    


    .EmptyFocusedRow, .EmptyFocusedRow td {
        background: none !important;
    }


    $(document).ready(function () {
        //$('.fodTitle').powerTip({ placement: 'sw', mouseOnToPopup: true }).data('powertip', function () {
        //    var r = $(&quot;&lt;p class='no-margin'> '&quot; + $(this).attr(&quot;data-title&quot;) + &quot;'&lt;/p>&quot;);
        //    return r;
        //});
    });

Amendments#1Add
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
        setMaxlengthAttribute(true);
        if (!($(currentActivatedForm).attr('islocked') == &quot;True&quot;)) {
            $(&quot;#SpPlusIcon&quot;).css(&quot;display&quot;,&quot;none&quot;);
        }
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });
   



  
    
        
            
                
                        
                
                
                    
                
                
                        
                
                
            
        
    
    

	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems('addendumGridView_7294_EEG_DXSE',[[['dxeButtonEditButtonHover_Metropolis'],[''],['B-100']]]);
ASPx.AddPressedItems('addendumGridView_7294_EEG_DXSE',[[['dxeButtonEditButtonPressed_Metropolis'],[''],['B-100']]]);
ASPx.AddDisabledItems('addendumGridView_7294_EEG_DXSE',[[['dxeDisabled_Metropolis'],[''],['','I']],[['dxeDisabled_Metropolis dxeButtonDisabled_Metropolis'],[''],['B-100'],,[[{'spriteCssClass':'dxEditors_edtClearDisabled_Metropolis'}]],['Img']]]);
ASPx.createControl(ASPxClientButtonEdit,'addendumGridView_7294_EEG_DXSE','',{'uniqueID':'addendumGridView_7294_EEG$DXSE','scStates':2,'scPostfix':'Metropolis','stateObject':{'rawValue':''},'nullText':'Search Existing Amendments','forceShowClearButtonAlways':true},null,null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''},{'key':'N','className':'dxeNullText_Metropolis','cssText':''}]});

//-->

				
			
		
			
				
					
						
					
						
							
								Date 
							
						
							
								Notes 
							
						
							
								Status 
							
						
							
								Source 
							
						
							
								Created By 
							
						
					
				
			
		
			
				
					
				
					
						No data to display
					
				
			
		
			
				
					
						
							Expand All
						
							Collapse All
						
							Sort Ascending
						
							Sort Descending
						
							Clear Sorting
						
							Group By This Column
						
							Ungroup
						
							Group Panel
						
							Show Column
						
							Hide Column
						
							Show Customization Dialog
						
							Column Chooser
						
							Clear Filter
						
							Search Panel
						
							Filter Builder...
						
							Filter Row
						
							Filter Row Menu
						
							Footer
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems('addendumGridView_7294_EEG_DXContextMenu_Columns',[[[''],[''],['DXME_']],[['dxm-hovered',''],['',''],['DXI0_','DXI1_','DXI2_','DXI3_','DXI4_','DXI5_','DXI6_','DXI7_','DXI8_','DXI9_','DXI10_','DXI11_','DXI12_','DXI13_','DXI14_','DXI15_','DXI16_','DXI17_'],['','T'],[[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[{'spriteCssClass':'dxGridView_gvCMShowCustDialogHover_Metropolis'}],[null],[null],[null],[null],[null],[null],[null]],['Img','PImg']]]);
ASPx.AddSelectedItems('addendumGridView_7294_EEG_DXContextMenu_Columns',[[['dxm-checked',''],['',''],['DXI2_','DXI3_','DXI7_','DXI11_','DXI13_','DXI15_','DXI16_','DXI17_'],['','T'],[[null],[null],[null],[null],[null],[{'spriteCssClass':'dxWeb_mSubMenuItemChecked_Metropolis'}],[{'spriteCssClass':'dxWeb_mSubMenuItemChecked_Metropolis'}],[{'spriteCssClass':'dxWeb_mSubMenuItemChecked_Metropolis'}]],['Img','PImg']]]);
ASPx.AddDisabledItems('addendumGridView_7294_EEG_DXContextMenu_Columns',[[['dxm-disabled'],[''],['DXI0_','DXI1_','DXI2_','DXI3_','DXI4_','DXI5_','DXI6_','DXI7_','DXI8_','DXI9_','DXI10_','DXI11_','DXI12_','DXI13_','DXI14_','DXI15_','DXI16_','DXI17_'],['','T'],[[{'spriteCssClass':'dxGridView_gvCMFullExpandDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMFullCollapseDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMSortAscendingDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMSortDescendingDisabled_Metropolis'}],[null],[{'spriteCssClass':'dxGridView_gvCMGroupByColumnDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMGroupByColumnDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMShowGroupPanelDisabled_Metropolis'}],[null],[null],[{'spriteCssClass':'dxGridView_gvCMShowCustDialogDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMShowCustomizationWindowDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMClearFilterDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMShowSearchPanelDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMShowFilterEditorDisabled_Metropolis'}],[null],[null],[null]],['Img','PImg']]]);
ASPx.createControl(ASPxClientPopupMenu,'addendumGridView_7294_EEG_DXContextMenu_Columns','',{'uniqueID':'addendumGridView_7294_EEG$DXContextMenu_Columns','cpItemsCommands':{'0':'FullExpand','1':'FullCollapse','2':'SortAscending','3':'SortDescending','4':'ClearSorting','5':'GroupByColumn','6':'UngroupColumn','7':'ShowGroupPanel','8':'ShowColumn','9':'HideColumn','10':'ShowCustomizationDialog','11':'ShowCustomizationWindow','12':'ClearFilter','13':'ShowSearchPanel','14':'ShowFilterEditor','15':'ShowFilterRow','16':'ShowFilterRowMenu','17':'ShowFooter'},'cpItemsInfo':{'0':[[1],[0,[-1]],[1]],'1':[[1],[0,[-1]],[1]],'2':[[0,[-1]],[0,[-1]],[1]],'3':[[0,[-1]],[0,[-1]],[1]],'4':[[1],[1],[1]],'5':[[0,[-1]],[0,[-1]],[1]],'6':[[1],[0,[-1]],[1]],'7':[[0],[0],[1]],'8':[[1],[0,[-1]],[1]],'9':[[1],[0,[-1]],[1]],'10':[[1],[0],[1]],'11':[[1],[0],[1]],'12':[[1],[0,[-1]],[1]],'13':[[0,[-1]],[0,[-1]],[0,[-1]]],'14':[[1],[0],[1]],'15':[[0,[-1]],[0,[-1]],[1]],'16':[[1],[0,[-1]],[1]],'17':[[0],[0],[1]]},'cpType':1,'renderData':{'':[[0],[1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12],[13],[14],[15],[16],[17]]},'allowCheckItems':true,'checkedState':'','itemCheckedGroups':[['2'],['3'],['7'],['11'],['13'],['15'],['16'],['17']],'isContextMenu':true},{'ItemClick':function(s,e){ASPx.GVContextMenuItemClick('addendumGridView_7294_EEG',e)}},null,{'items':[{'name':'FullExpand'},{'name':'FullCollapse'},{'beginGroup':true,'name':'SortAscending'},{'name':'SortDescending'},{'name':'ClearSorting'},{'beginGroup':true,'name':'GroupByColumn'},{'name':'UngroupColumn'},{'name':'ShowGroupPanel'},{'beginGroup':true,'name':'ShowColumn'},{'name':'HideColumn'},{'name':'ShowCustomizationDialog'},{'name':'ShowCustomizationWindow'},{'beginGroup':true,'name':'ClearFilter'},{'name':'ShowSearchPanel'},{'name':'ShowFilterEditor'},{'name':'ShowFilterRow'},{'name':'ShowFilterRowMenu'},{'name':'ShowFooter'}]});

//-->

			
				
					
						
							Expand
						
							Collapse
						
							Expand Detail
						
							Collapse Detail
						
							New
						
							Edit
						
							Delete
						
							Group Summary
						
							
						
							Refresh
						
					
				
			
				
					
						
							Sum
						
							Min
						
							Max
						
							Count
						
							Average
						
							None
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems('addendumGridView_7294_EEG_DXContextMenu_Rows',[[[''],[''],['DXME_','DXME7_','DXMBC7_']],[['dxm-hovered','',''],['','',''],['DXI0_','DXI1_','DXI2_','DXI3_','DXI4_','DXI5_','DXI6_','DXI7_','DXI8_'],['','P','T']],[['dxm-hovered',''],['',''],['DXI7i0_','DXI7i1_','DXI7i2_','DXI7i3_','DXI7i4_','DXI7i5_'],['','T']]]);
ASPx.AddSelectedItems('addendumGridView_7294_EEG_DXContextMenu_Rows',[[['dxm-checked',''],['',''],['DXI7i0_','DXI7i1_','DXI7i2_','DXI7i3_','DXI7i4_'],['','T']]]);
ASPx.AddDisabledItems('addendumGridView_7294_EEG_DXContextMenu_Rows',[[['dxm-disabled'],[''],['DXI0_','DXI1_','DXI2_','DXI3_','DXI4_','DXI5_','DXI6_','DXI7_','DXI8_'],['','P','T'],[[{'spriteCssClass':'dxGridView_gvCMExpandRowDisabled_Metropolis'},null],[{'spriteCssClass':'dxGridView_gvCMCollapseRowDisabled_Metropolis'},null],[{'spriteCssClass':'dxGridView_gvCMExpandDetailRowDisabled_Metropolis'},null],[{'spriteCssClass':'dxGridView_gvCMCollapseDetailRowDisabled_Metropolis'},null],[{'spriteCssClass':'dxGridView_gvCMNewRowDisabled_Metropolis'},null],[{'spriteCssClass':'dxGridView_gvCMEditRowDisabled_Metropolis'},null],[{'spriteCssClass':'dxGridView_gvCMDeleteRowDisabled_Metropolis'},null],[null,null],[{'spriteCssClass':'dxGridView_gvCMRefreshDisabled_Metropolis'},null]],['Img','PImg']],[['dxm-disabled'],[''],['DXI7i0_','DXI7i1_','DXI7i2_','DXI7i3_','DXI7i4_','DXI7i5_'],['','T'],[[{'spriteCssClass':'dxGridView_gvCMSummarySumDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMSummaryMinDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMSummaryMaxDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMSummaryCountDisabled_Metropolis'}],[{'spriteCssClass':'dxGridView_gvCMSummaryAverageDisabled_Metropolis'}],[null]],['Img','PImg']]]);
ASPx.createControl(ASPxClientPopupMenu,'addendumGridView_7294_EEG_DXContextMenu_Rows','',{'uniqueID':'addendumGridView_7294_EEG$DXContextMenu_Rows','cpItemsCommands':{'0':'ExpandRow','1':'CollapseRow','2':'ExpandDetailRow','3':'CollapseDetailRow','4':'NewRow','5':'EditRow','6':'DeleteRow','7':'GroupSummaryMenu','7i0':'GroupSummarySum','7i1':'GroupSummaryMin','7i2':'GroupSummaryMax','7i3':'GroupSummaryCount','7i4':'GroupSummaryAverage','7i5':'GroupSummaryNone','8':'Refresh'},'cpItemsInfo':{'0':[[1],[1],[1]],'1':[[1],[1],[1]],'2':[[1],[1],[1]],'3':[[1],[1],[1]],'4':[[0],[0],[1]],'5':[[1],[1],[1]],'6':[[1],[1],[1]],'7':[[1],[1],[1]],'7i0':[[1],[1],[1]],'7i1':[[1],[1],[1]],'7i2':[[1],[1],[1]],'7i3':[[1],[1],[1]],'7i4':[[1],[1],[1]],'7i5':[[1],[1],[1]],'8':[[0],[0],[1]]},'cpType':2,'renderData':{'':[[0],[1],[2],[3],[4],[5],[6],[7],[8]],'7':[[0],[1],[2],[3],[4],[5]]},'allowCheckItems':true,'checkedState':'','itemCheckedGroups':[['7i0'],['7i1'],['7i2'],['7i3'],['7i4']],'isContextMenu':true},{'ItemClick':function(s,e){ASPx.GVContextMenuItemClick('addendumGridView_7294_EEG',e)}},null,{'items':[{'beginGroup':true,'name':'ExpandRow'},{'name':'CollapseRow'},{'beginGroup':true,'name':'ExpandDetailRow'},{'name':'CollapseDetailRow'},{'beginGroup':true,'name':'NewRow'},{'name':'EditRow'},{'name':'DeleteRow'},{'items':[{'name':'GroupSummarySum'},{'name':'GroupSummaryMin'},{'name':'GroupSummaryMax'},{'name':'GroupSummaryCount'},{'name':'GroupSummaryAverage'},{'beginGroup':true,'name':'GroupSummaryNone'}],'beginGroup':true,'name':'GroupSummaryMenu'},{'beginGroup':true,'name':'Refresh'}]});

//-->

	

&lt;!--
ASPx.createControl(MVCxClientGridView,'addendumGridView_7294_EEG','',{'callBack':function(arg) { ; },'stateObject':{'keys':[],'resizingState':'','callbackState':'/QEABH7hZeGGM/Qm5RGbMVYxTwBxn9Hc7T8sAKhydrFw1TSSwHy6kfsIueNOYirKhaTX/hQl8AzjYyTfiblVmfRaDBZBERdjpW3lPA7hfffImcgMXZSvDnaITPRqrq1Wz9qzrQbvjmjGJYzQLj6HSOdm6t/LB6s6qi2rFGVoM55YzGOCPsBhwhelCW9rqfj9VORWD7TVItklXYfDCEbvKWmQsxiEh33bOQB+e3O5O9Y90EABiege243j79eQTx/fMrZNhykjBKjWUcBRxwFHy/jdDyze83YTC6YHdD0HU3M7599bEfzA5N+fr+ag7c0fQgXnAKAVOTQ6I/zA79ePnV8Gf0+YJh8PKOPKulB3lIE5LJ5JH92jICGt3kZPpi5dbw6e2F0iZuuigtM4ITzanL1rqehooLZA44LplSmfZV47kYsEkhmYTUn1ImwU1CIPHnuHa2LwoN5ibiayNtMXWoQRDxKMTamnm2BfL2B+A81ONv1+ybTN7fAyUQr68O2mx9y+LA==','groupLevelState':{},'scrollState':null,'selection':''},'cp_CREATED_DATE':'Date','cp_NOTES':'Notes','cp_ADDENDUM_STATUS':'Status','cp_SOURCE':'Source','cp_CREATED_BY':'Created By','cp_gridRowData':{},'cp_gridData':{},'callBacksEnabled':true,'pageRowCount':0,'pageRowSize':10,'pageIndex':-1,'pageCount':0,'selectedWithoutPageRowCount':0,'visibleStartIndex':0,'focusedRowIndex':-1,'allowFocusedRow':false,'allowSelectByItemClick':false,'allowSelectSingleRowOnly':false,'vertScroll':2,'callbackOnFocusedRowChanged':false,'callbackOnSelectionChanged':false,'editState':0,'editItemVisibleIndex':-1,'searchPanelFilter':'','allowDelete':true,'allowEdit':true,'allowInsert':true,'columnProp':[[0,,,'CREATED_DATE',,3,,,0],[1,,,'NOTES',,,,,1],[2,,,'ADDENDUM_STATUS',,,,,2],[3,,,'SOURCE',,,,,3],[4,,,'CREATED_BY',,,,,4]],'editMode':2,'indentColumnCount':0,'allowChangeColumnHierarchy':false,'allowMultiColumnAutoFilter':false,'columnResizeMode':2,'editingItemVisibleIndex':-1,'callbackUrl':'/ComplexElements/AddendumGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=7294','keyName':'RowGuid'},{'Init':addendumGridView_Init,'ContextMenuItemClick':encGrid_OnContextMenuItemClick});
ASPxClientGridBase.PostponeInitialize('addendumGridView_7294_EEG',({'commandButtonIDs':[],'styleInfo':{'ei':'&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;5&quot;>&lt;/td>\r\n&lt;/tr>','fc':{'css':'dxgvFocusedCell_Metropolis'},'bec':{'css':'dxgvBatchEditCell_Metropolis dxgv'},'bemc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgv'},'bemergmc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv'},'sel':{'css':'dxgvSelectedRow_Metropolis'},'fi':{'css':'dxgvFocusedRow_Metropolis'},'fgi':{'css':'dxgvFocusedGroupRow_Metropolis'}}}));

//-->


    


   div[data-elementinternalname=Addendums] .dxgvSearchPanel_Metropolis > table {
        width: 35%;
    }
   div[data-elementinternalname=Addendums] .dxgvSearchPanel_Metropolis.dxgvSearchPanel_Metropolis_Right_new > table {
        width: 40%;
    }
Signatures#1Add
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find('span[class*=&quot;disabled&quot;]').parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });





    
        
            
                
                        
                
                
                        
                
                
                        
                    
                
                
            
        
    
    


	
		
			
				
					
						
					
						
							
								Type 
							
						
							
								User 
							
						
							
								Active? 
							
						
							
								Signed? 
							
						
							
								Sign 
							
						
							
								  
							
						
							
								
							
						
					
				
			
		
			
				
					
				
					ProviderPatient PortalYesNo
				
					
						No data to display
					
				
					  NoNo
				
			
		
			
				
					
						
							Edit Value List
						
							
						
					
				
			
				
					
						
							User Types
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems('signatureGridView_f659_EEG_DXContextMenu_Columns',[[[''],[''],['DXME_','DXME0_','DXMBC0_']],[['dxm-hovered','',''],['','',''],['DXI0_'],['','P','T']],[['dxm-hovered',''],['',''],['DXI0i0_'],['','T']]]);
ASPx.AddDisabledItems('signatureGridView_f659_EEG_DXContextMenu_Columns',[[['dxm-disabled'],[''],['DXI0_'],['','P','T']],[['dxm-disabled'],[''],['DXI0i0_'],['','T']]]);
ASPx.createControl(ASPxClientPopupMenu,'signatureGridView_f659_EEG_DXContextMenu_Columns','',{'uniqueID':'signatureGridView_f659_EEG$DXContextMenu_Columns','cpItemsCommands':{'0':'Custom','0i0':'Custom'},'cpItemsInfo':{'0':[[0],[0],[1]],'0i0':[[0],[0],[1]]},'cpType':1,'renderData':{'':[[0]],'0':[[0]]},'isContextMenu':true},{'ItemClick':function(s,e){ASPx.GVContextMenuItemClick('signatureGridView_f659_EEG',e)}},null,{'items':[{'items':[{'name':'editVLSimple_User_Types'}],'name':'gv_editValueList'}]});

//-->

			
				
					
						
							Edit Value List
						
							
						
					
				
			
				
					
						
							User Types
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems('signatureGridView_f659_EEG_DXContextMenu_Rows',[[[''],[''],['DXME_','DXME0_','DXMBC0_']],[['dxm-hovered','',''],['','',''],['DXI0_'],['','P','T']],[['dxm-hovered',''],['',''],['DXI0i0_'],['','T']]]);
ASPx.AddDisabledItems('signatureGridView_f659_EEG_DXContextMenu_Rows',[[['dxm-disabled'],[''],['DXI0_'],['','P','T']],[['dxm-disabled'],[''],['DXI0i0_'],['','T']]]);
ASPx.createControl(ASPxClientPopupMenu,'signatureGridView_f659_EEG_DXContextMenu_Rows','',{'uniqueID':'signatureGridView_f659_EEG$DXContextMenu_Rows','cpItemsCommands':{'0':'Custom','0i0':'Custom'},'cpItemsInfo':{'0':[[0],[0],[1]],'0i0':[[0],[0],[1]]},'cpType':2,'renderData':{'':[[0]],'0':[[0]]},'isContextMenu':true},{'ItemClick':function(s,e){ASPx.GVContextMenuItemClick('signatureGridView_f659_EEG',e)}},null,{'items':[{'items':[{'name':'editVLSimple_User_Types'}],'name':'gv_editValueList'}]});

//-->

			

	
		
	

	
		
			
				
					
						
							
								
									 
								
							

							
						
					
				
&lt;!--
ASPx.createControl(MVCxClientListBox,'PRACTICE_PERSON_TYPE_GF_f659_GF_DDD_L','',{'uniqueID':'PRACTICE_PERSON_TYPE_GF_f659_GF$DDD$L','scStates':6,'scPostfix':'Metropolis','stateObject':{'CustomCallback':''},'isSyncEnabled':false,'isComboBoxList':true,'hasSampleItem':true,'hoverClasses':['dxeListBoxItemHover_Metropolis'],'selectedClasses':['dxeListBoxItemSelected_Metropolis'],'disabledClasses':['dxeDisabled_Metropolis'],'itemsInfo':[{'value':'Provider','texts':['Provider']},{'value':'Nurse','texts':['Nurse']},{'value':'PA','texts':['PA']},{'value':'Tech','texts':['Tech']},{'value':'Other Staff','texts':['Other Staff']}]},{'SelectedIndexChanged':function (s, e) { ASPx.CBLBSelectedIndexChanged('PRACTICE_PERSON_TYPE_GF_f659_GF', e); },'ItemClick':function (s, e) { ASPx.CBLBItemMouseUp('PRACTICE_PERSON_TYPE_GF_f659_GF', e); }},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			
		
	

&lt;!--
ASPx.AddHoverItems('PRACTICE_PERSON_TYPE_GF_f659_GF_DDD',[[['dxpc-closeBtnHover'],[''],['HCB-1']]]);
ASPx.createControl(ASPxClientPopupControl,'PRACTICE_PERSON_TYPE_GF_f659_GF_DDD','',{'uniqueID':'PRACTICE_PERSON_TYPE_GF_f659_GF$DDD','adjustInnerControlsSizeOnShow':false,'popupAnimationType':'slide','closeAction':'CloseButton','popupHorizontalAlign':'LeftSides','popupVerticalAlign':'Below'},{'Shown':function (s, e) { ASPx.DDBPCShown('PRACTICE_PERSON_TYPE_GF_f659_GF', e); }});

//-->

&lt;!--
ASPx.AddHoverItems('PRACTICE_PERSON_TYPE_GF_f659_GF',[[['dxeButtonEditButtonHover_Metropolis'],[''],['B-1']]]);
ASPx.RemoveHoverItems('PRACTICE_PERSON_TYPE_GF_f659_GF',[[['B-100']]]);
ASPx.AddPressedItems('PRACTICE_PERSON_TYPE_GF_f659_GF',[[['dxeButtonEditButtonPressed_Metropolis'],[''],['B-1']]]);
ASPx.RemovePressedItems('PRACTICE_PERSON_TYPE_GF_f659_GF',[[['B-100']]]);
ASPx.AddDisabledItems('PRACTICE_PERSON_TYPE_GF_f659_GF',[[['dxeDisabled_Metropolis'],[''],['','I']],[['dxeDisabled_Metropolis dxeButtonDisabled_Metropolis'],[''],['B-1'],,[[{'spriteCssClass':'dxEditors_edtDropDownDisabled_Metropolis'}]],['Img']]]);
ASPx.RemoveDisabledItems('PRACTICE_PERSON_TYPE_GF_f659_GF',[[['B-100'],]]);
ASPx.createControl(MVCxClientComboBox,'PRACTICE_PERSON_TYPE_GF_f659_GF','',{'scStates':2,'scPostfix':'Metropolis','autoCompleteAttribute':{'name':'autocomplete','value':'off'},'incrementalFilteringMode':'None','lastSuccessValue':null,'islastSuccessValueInit':true},{'GotFocus':ComboBox_ShowDropDown,'LostFocus':signature_LostFocus,'KeyDown':signature_KeyDown,'SelectedIndexChanged':function(s, e) { GetDXControlsByName('PRACTICE_PERSON_GF_f659_GF').PerformCallback();}},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			


	
		Loading…
	



	
		
	

	
		
			
				
					
						
							
								
									 
								
							

							
								

							
						
					
				
&lt;!--
ASPx.createControl(MVCxClientListBox,'PRACTICE_PERSON_GF_f659_GF_DDD_L','',{'uniqueID':'PRACTICE_PERSON_GF_f659_GF$DDD$L','scStates':6,'scPostfix':'Metropolis','stateObject':{'CustomCallback':''},'isSyncEnabled':false,'isComboBoxList':true,'hasSampleItem':true,'isCallbackMode':true,'callbackPageSize':100,'isHasFakeRow':true,'hoverClasses':['dxeListBoxItemHover_Metropolis'],'selectedClasses':['dxeListBoxItemSelected_Metropolis'],'disabledClasses':['dxeDisabled_Metropolis'],'itemsInfo':[]},{'SelectedIndexChanged':function (s, e) { ASPx.CBLBSelectedIndexChanged('PRACTICE_PERSON_GF_f659_GF', e); },'ItemClick':function (s, e) { ASPx.CBLBItemMouseUp('PRACTICE_PERSON_GF_f659_GF', e); }},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			
		
	

&lt;!--
ASPx.AddHoverItems('PRACTICE_PERSON_GF_f659_GF_DDD',[[['dxpc-closeBtnHover'],[''],['HCB-1']]]);
ASPx.createControl(ASPxClientPopupControl,'PRACTICE_PERSON_GF_f659_GF_DDD','',{'uniqueID':'PRACTICE_PERSON_GF_f659_GF$DDD','adjustInnerControlsSizeOnShow':false,'popupAnimationType':'slide','closeAction':'CloseButton','popupHorizontalAlign':'LeftSides','popupVerticalAlign':'Below'},{'Shown':function (s, e) { ASPx.DDBPCShown('PRACTICE_PERSON_GF_f659_GF', e); }});

//-->

&lt;!--
ASPx.AddHoverItems('PRACTICE_PERSON_GF_f659_GF',[[['dxeButtonEditButtonHover_Metropolis'],[''],['B-1']]]);
ASPx.RemoveHoverItems('PRACTICE_PERSON_GF_f659_GF',[[['B-100']]]);
ASPx.AddPressedItems('PRACTICE_PERSON_GF_f659_GF',[[['dxeButtonEditButtonPressed_Metropolis'],[''],['B-1']]]);
ASPx.RemovePressedItems('PRACTICE_PERSON_GF_f659_GF',[[['B-100']]]);
ASPx.AddDisabledItems('PRACTICE_PERSON_GF_f659_GF',[[['dxeDisabled_Metropolis'],[''],['','I']],[['dxeDisabled_Metropolis dxeButtonDisabled_Metropolis'],[''],['B-1'],,[[{'spriteCssClass':'dxEditors_edtDropDownDisabled_Metropolis'}]],['Img']]]);
ASPx.RemoveDisabledItems('PRACTICE_PERSON_GF_f659_GF',[[['B-100'],]]);
ASPx.createControl(MVCxClientComboBox,'PRACTICE_PERSON_GF_f659_GF','',{'callBack':function(arg) { ; },'scStates':2,'scPostfix':'Metropolis','autoCompleteAttribute':{'name':'autocomplete','value':'off'},'incrementalFilteringMode':'None','isCallbackMode':true,'lastSuccessValue':null,'islastSuccessValueInit':true,'callbackUrl':'/ComplexElements/GetUsersByType'},{'LostFocus':signature_LostFocus,'KeyDown':signature_KeyDown,'BeginCallback':function(s, e) {e.customArgs['dropDownName'] ='PRACTICE_PERSON_GF_f659_GF';e.customArgs['cascadeDropDownName'] ='PRACTICE_PERSON_TYPE_GF_f659_GF';e.customArgs['keyDownEventName'] ='signature_KeyDown';e.customArgs['lostFocusEventName'] ='signature_LostFocus';e.customArgs['valueListInternaName'] ='';e.customArgs['fieldName'] ='PRACTICE_PERSON';e.customArgs['cascadeFieldName'] ='PRACTICE_PERSON_TYPE';e.customArgs['cascadeFieldValue'] = GetDXControlsByName('PRACTICE_PERSON_TYPE_GF_f659_GF').GetValue();},'SelectedIndexChanged':SignatureonComboBoxSelectedIndexChanged},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'IS_ACTIVE_USER_GF_f659_GF','',{'scStates':4,'scPostfix':'Metropolis','readOnly':true},{'Init':function(s,e){$(s.GetInputElement()).attr('data-nochangeevent','True');},'LostFocus':signature_LostFocus,'KeyDown':signature_KeyDown},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,'HAS_SIGNED_GF_f659_GF','',{'scStates':4,'scPostfix':'Metropolis','stateObject':{'rawValue':''},'readOnly':true,'nullText':'No'},{'Init':function(s,e){$(s.GetInputElement()).attr('data-nochangeevent','True');},'LostFocus':signature_LostFocus,'KeyDown':signature_KeyDown},null,{'decorationStyles':[{'key':'F','className':'dxeFocused_Metropolis','cssText':''},{'key':'N','className':'dxeNullText_Metropolis','cssText':''}]});

//-->

			

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,'signatureGridView_f659_EEG','',{'callBack':function(arg) { ; },'stateObject':{'keys':['e378ce03-8116-4d94-b542-1c3909850f4c'],'resizingState':'','callbackState':'UBT9G8BJrzDLM5gbBxe1NQfB9MUk9RxWZ1zoxlDhNQCEpNfEGNmJgG9t3XYCiwuxHcIWwdP45in5jj1i4gQJKrECx/p8iuMj8NZ/qosVWBew7k+k3dpoiKDXAsDQO/vcLXH21OVat+/BRxG+l/05p+KSa3LZ6XwHHg/zkxZlg5jfK2RDrkbK9HTh4taSjftpdDjtqPvYpdcmJqxlLQdu+f8G3ArjbV8B7LVvx2+9ZgJCIFknghwSr8XPIVpsxdG/YSzeNGSKpcotCWnMRfZrMoY4PR/s+MfOGS3LIBeeOYN/bTeYVh1i1ktS69YC+GN6C4wB8cZ3ntrk9XhNN+wTpasjhuaMtGRxq+mRpFRypdiokH/CE/hyTYJ0UvXNGHOZW0FCfE6osOJo3Zq6muHN4gQRPkp4V5pZZIF+pRHyZDtYVsEe5MfmM+uteZTN53dp/lvrKIvEgcHpWSzmcYYCdeyY6NPEICp7kF205gsINQ199rYG/w3hdS9JE7r3fCaUu71Y3MFJhpeW/OGx1HUnvLP1Dh3LhK7FAZzSzl+J1toi9VuuE8nZkuZNIZya0X3rwKk5FQ==','groupLevelState':{},'scrollState':null,'selection':'','batchEditClientModifiedValues':{}},'cp_PRACTICE_PERSON_TYPE':'Type','cp_PRACTICE_PERSON':'User','cp_IS_ACTIVE_USER':'Active?','cp_HAS_SIGNED':'Signed?','cp_SIGNATURE_DATE_TIME':'Date &amp; Time','cp_':'','cp_gridRowData':{'e378ce03-8116-4d94-b542-1c3909850f4c':{'RowGuid':'e378ce03-8116-4d94-b542-1c3909850f4c','RecordId':'230','PRACTICE_PERSON_TYPE':'Provider','PRACTICE_PERSON':'Patient Portal','IS_ACTIVE_USER':true,'HAS_SIGNED':false}},'cp_gridData':{'e378ce03-8116-4d94-b542-1c3909850f4c':'230:12'},'callBacksEnabled':true,'pageRowCount':1,'pageRowSize':10,'pageIndex':-1,'pageCount':1,'selectedWithoutPageRowCount':0,'checkBoxImageProperties':{'4':['dxWeb_edtCheckBoxChecked_Metropolis','dxWeb_edtCheckBoxUnchecked_Metropolis','dxWeb_edtCheckBoxGrayed_Metropolis'],'8':['dxWeb_edtCheckBoxCheckedDisabled_Metropolis','dxWeb_edtCheckBoxUncheckedDisabled_Metropolis','dxWeb_edtCheckBoxGrayedDisabled_Metropolis']},'icbFocusedStyle':['dxICBFocused_Metropolis',''],'visibleStartIndex':0,'focusedRowIndex':-1,'allowFocusedRow':false,'allowSelectByItemClick':false,'allowSelectSingleRowOnly':false,'vertScroll':2,'callbackOnFocusedRowChanged':false,'callbackOnSelectionChanged':false,'editState':0,'editItemVisibleIndex':-1,'allowBatchEditing':true,'batchEditClientState':{'binaryImageColumnsDisplayHtml':{},'hiddenEditorColumnIndices':[],'validationInfo':null,'editColumnIndices':[1,2,3,4,6,7],'startEditAction':0,'comboBoxCallbackModeItemsInfo':{},'preventUpdateCellTextForDataItemTemplate':0,'comboBoxColumnsDisplayHtml':{},'ellipsisColumns':[],'templateColumnIndices':[1,2,3,4,6,7],'isNewRowOnTop':false,'columnsNullDisplayTextInfo':{'4':'No'},'validateOnEndEdit':1,'dataItemTemplateColumnIndices':[3,4,6,7],'colorColumnsDisplayHtml':{},'editMode':1,'allowEndEditOnError':1,'progressBarColumnIndices':[],'comboBoxColumnsWithServerEventsIndices':[],'updateInfo':{},'highlightDeletedItems':false,'nonEditableColumnValues':{'e378ce03-8116-4d94-b542-1c3909850f4c':{}},'checkColumnsDisplayHtml':{}},'batchEditPageValues':{'e378ce03-8116-4d94-b542-1c3909850f4c':{'1':'Provider','2':'Patient Portal','3':true,'4':false,'6':null},'NIV':{'1':null,'2':null,'3':null,'4':null,'6':null}},'searchPanelFilter':'','selectAllBtnStateWithoutPage':null,'selectAllSettings':[{'index':0,'mode':1}],'allowFocusedCell':true,'allowDelete':true,'allowEdit':true,'allowInsert':true,'columnProp':[[0,,,,0,,,,100,,1,,,0],[1,,,'PRACTICE_PERSON_TYPE',,,,,0],[2,,'PRACTICE_PERSON_NAME','PRACTICE_PERSON',,,,,1],[3,,,'IS_ACTIVE_USER',,,,,2],[4,,,'HAS_SIGNED',,,,,3],[5,0,,'SIGNATURE_DATE_TIME',,,,,,,,,,,,,1],[6,,,,0,,,,4,,,,,0],[7,,'No_Focus',,0,,,,5,,,,,0]],'editMode':4,'indentColumnCount':0,'allowChangeColumnHierarchy':false,'allowMultiColumnAutoFilter':false,'columnResizeMode':2,'editingItemVisibleIndex':-1,'callbackUrl':'/ComplexElements/SignatureGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=f659','keyName':'RowGuid'},{'Init':signatureGridView_Init,'BeginCallback':enc_grid_before_callback,'EndCallback':signature_grid_end_callback,'BatchEditStartEditing':signatureGridView_BatchEditStartEditing,'BatchEditEndEditing':signatureGridView_BatchEditEndEditing,'FocusedCellChanging':onFocusedCellChanging,'ContextMenuItemClick':encGrid_OnContextMenuItemClick,'BatchEditRowValidating':signatureGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize('signatureGridView_f659_EEG',({'commandButtonIDs':[],'styleInfo':{'ei':'&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>','fc':{'css':'dxgvFocusedCell_Metropolis'},'bec':{'css':'dxgvBatchEditCell_Metropolis dxgv'},'bemc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgv'},'bemergmc':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv'},'bemc2':{'css':'dxgvBatchEditModifiedCell_Metropolis wm-hide dxgv'},'bemergmc2':{'css':'dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis wm-hide dxgv'},'sel':{'css':'dxgvSelectedRow_Metropolis'},'fi':{'css':'dxgvFocusedRow_Metropolis'},'fgi':{'css':'dxgvFocusedGroupRow_Metropolis'}}}));

//-->


    $('.tooltip_sign_off_btn_elem').powerTip({ placement: 's', mouseOnToPopup: true }).data('powertip', function () {
        debugger
        if ($(this).attr('data-enc-sign-off-date') != undefined) {
            var r = $(&quot;&lt;div class='no-margin font14 pad15'>&lt;div class='float-left align-right'>&lt;b>Signed: &quot; + $(this).attr('data-enc-sign-off-date') + &quot;&lt;/b>&lt;/div>&quot;);
            return r;
        }
        else {
            return &quot;&quot;;
        }
        });


    



</value>
      <webElementGuid>7b038cd2-5f08-4f7c-b4dd-255e7bb235a6</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>parent</name>
      <type>Main</type>
      <value>md5.v1-a83f4c3fe4c93a14f9c7483f1ef15bda</value>
      <webElementGuid>5273d404-29d9-45a4-bb61-316e4dd60eb4</webElementGuid>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>//*[(@aria-labelledby = 'encTabList_2') and @class and contains(concat(' ', normalize-space(@class), ' '), ' enContentHt ')]</value>
      <webElementGuid>d32bcf40-2de2-4916-9765-372d778fccbd</webElementGuid>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:attributes</name>
      <type>Main</type>
      <value>//*[(@aria-labelledby = 'encTabList_2') and @class and contains(concat(' ', normalize-space(@class), ' '), ' enContentHt ')]</value>
      <webElementGuid>fd2999e1-9768-4d39-941e-6f5feb175b29</webElementGuid>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:customAttributes</name>
      <type>Main</type>
      <value>//div[@id = 'encounterform13ea63070d' and (text() = concat(&quot;Final Diagnoses#1AddAssessmentsPlansExclude from IPBaseline ExamAnnual exam
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
    });




    
    



    //0049539: Data Loss - Final RX data in Encounters and notes on Patient records disappeared
    var planDivLength = $(&quot; , &quot;'&quot; , &quot;div#encounterPlansData&quot; , &quot;'&quot; , &quot;).length;
    if (planDivLength > 1) {
        var formPageRelId = $(&quot; , &quot;'&quot; , &quot;[id^=encTabList_]&quot; , &quot;'&quot; , &quot;).closest(&quot; , &quot;'&quot; , &quot;.active-toggle&quot; , &quot;'&quot; , &quot;).parent().attr(&quot; , &quot;'&quot; , &quot;data-formpagerelid&quot; , &quot;'&quot; , &quot;);
        if (!checknullBlankUndefind(formPageRelId) &amp;&amp; checknullBlankUndefind($(&quot; , &quot;'&quot; , &quot;#IsOnEncFormScreen&quot; , &quot;'&quot; , &quot;).val())) {
            for (var i = 0; i &lt; planDivLength; i++) {
                var findClosestDivForId = $(&quot; , &quot;'&quot; , &quot;div#encounterPlansData&quot; , &quot;'&quot; , &quot;)[i].closest(&quot; , &quot;'&quot; , &quot;[id^=encformbase]&quot; , &quot;'&quot; , &quot;);
                if (!checknullBlankUndefind(findClosestDivForId)) {
                    var otherPageRelId = $(findClosestDivForId).attr(&quot; , &quot;'&quot; , &quot;data-formpagerelid&quot; , &quot;'&quot; , &quot;);
                    if (!checknullBlankUndefind(otherPageRelId) &amp;&amp; otherPageRelId != formPageRelId) {
                        var $finalDiagnosesDivLength = $(&quot;div[data-formpagerelid=&quot; + otherPageRelId + &quot;]&quot;).find(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;&quot; + &quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot; + &quot;&quot; , &quot;'&quot; , &quot;]&quot;);
                        if ($finalDiagnosesDivLength.length > 0) {
                            $finalDiagnosesDivLength.remove();
                        }
                        break;
                    }
                }
            }
        }
    }
    $(document).ready(function () {
        if (GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;) != null &amp;&amp; GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;).PreferenceValue == &quot;Y&quot;) {
            $(&quot; , &quot;'&quot; , &quot;textarea[name*=&quot;Final_Diagnoses.ASSESSMENT&quot;]&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;overflow&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;hidden&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;overflow&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;hidden&quot; , &quot;'&quot; , &quot;);
        }
        var planHt = $(&quot;div[data-ec_ctrldispname=&quot; , &quot;'&quot; , &quot;PLAN_HTML&quot; , &quot;'&quot; , &quot;]&quot;).height();
        $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, planHt + &quot; , &quot;'&quot; , &quot;px&quot; , &quot;'&quot; , &quot;);
        //Due to https://h00ghi19.maximeyes.com/ URL issue - data showing in new line so we changed below code
        var PlanLocalData = localStorage.getItem(&quot;PlanHTML&quot;);
        if (PlanLocalData != &quot;&quot; &amp;&amp; PlanLocalData != null) {
            var planHtmlData = $.parseHTML(PlanLocalData);
            checkLocaStorageforSetData(&quot;PlanHTML&quot;, &quot;&quot;);
        }
        else {
            var planHtmlData = ``; //MBT #35015, #35014 when plan data has &quot;`&quot; and &quot;&quot; , &quot;'&quot; , &quot;&quot;
        }

        if (planHtmlData != &quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;) $(&quot; , &quot;'&quot; , &quot;div[name*=PLAN_HTML]&quot; , &quot;'&quot; , &quot;).find(&quot; , &quot;'&quot; , &quot;div[id^=encounterPlansData]&quot; , &quot;'&quot; , &quot;).html(planHtmlData).trigger(&quot; , &quot;'&quot; , &quot;change&quot; , &quot;'&quot; , &quot;);
        //$(&quot; , &quot;'&quot; , &quot;textarea[name*=PLAN]&quot; , &quot;'&quot; , &quot;).css(&quot;visibility&quot;, &quot;hidden&quot;);
        $(&quot; , &quot;'&quot; , &quot;textarea[name*=PLAN][data-elementname=&quot;Final_Diagnoses&quot;]&quot; , &quot;'&quot; , &quot;).css(&quot;visibility&quot;, &quot;hidden&quot;);
    });

    if (($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;) != undefined &amp;&amp; $(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;).toLowerCase() == &quot;true&quot;)|| ($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;iseditable&quot; , &quot;'&quot; , &quot;) != undefined &amp;&amp; $(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;iseditable&quot; , &quot;'&quot; , &quot;).toLowerCase() == &quot;false&quot;)) {
        $(&quot;#encounterPlansData&quot;).bind(&quot;keydown&quot;, function (e) {
            e.preventDefault();
            return;
        });
    }


    //function CheckEncounterLockStat(e) {
    //    if ($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;).toLowerCase() == &quot;true&quot;) {
    //       // showConfirmationBar(&quot;Encounter Locked!&quot;, &quot;The selected encounter is locked. Do you want to add an amendment for this encounter?&quot;, &quot;OK&quot;, &quot;Cancel&quot;, &quot;&quot;);
    //        e.textContent = &quot;&quot;;
    //       // $(&quot;#OK&quot;).unbind(&quot; , &quot;'&quot; , &quot;click&quot; , &quot;'&quot; , &quot;);
    //        //$(&quot;#OK&quot;).bind(&quot; , &quot;'&quot; , &quot;click&quot; , &quot;'&quot; , &quot;, function () {
    //        //    e.textContent = &quot;&quot;;
    //        //    ShowModalPopup(&quot; , &quot;'&quot; , &quot;Patient Encounter Amendment&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;popup-30&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;GetAddendumNotes/PatientRecord&quot; , &quot;'&quot; , &quot;, null, null);
    //        //});
    //        //e.textContent = &quot;&quot;;
    //        //$(&quot;#Cancel&quot;).unbind(&quot; , &quot;'&quot; , &quot;Cancel&quot; , &quot;'&quot; , &quot;);
    //        //$(&quot;#Cancel&quot;).bind(&quot; , &quot;'&quot; , &quot;click&quot; , &quot;'&quot; , &quot;, function () {
    //        //    e.textContent = &quot;&quot;;
    //        //});
    //    }
    //}

    $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).bind(&quot; , &quot;'&quot; , &quot;blur&quot; , &quot;'&quot; , &quot;, function () {
        var plansData = getPlanText(&quot;#encounterPlansData&quot;, true);
        plansData = $(&quot; , &quot;'&quot; , &quot;&lt;div>&quot; , &quot;'&quot; , &quot; + plansData + &quot; , &quot;'&quot; , &quot;&lt;/div>&quot; , &quot;'&quot; , &quot;)[0].textContent;
        $(&quot; , &quot;'&quot; , &quot;textarea[name*=PLAN][data-elementname=&quot;Final_Diagnoses&quot;]&quot; , &quot;'&quot; , &quot;).val(plansData).trigger(&quot; , &quot;'&quot; , &quot;change&quot; , &quot;'&quot; , &quot;);
        submitChanges(false);

        var planHtmlElement = $(&quot; , &quot;'&quot; , &quot;div[name*=PLAN_HTML]&quot; , &quot;'&quot; , &quot;);
        if (currentActivatedForm != false) {
            var asHashKey = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;);
            asHashKey = asHashKey + &quot;~S~F_F~Final_Diagnoses.PLAN_HTML&quot;;
            var data = $.trim($(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).html());
            trackChanges(asHashKey, data, planHtmlElement);
            submitChanges(false);
        }

        // Save Child Record
        savePlansChildRecord(&quot;#encounterPlansData&quot;);
    });


    /*div#encounterPlansData {
        overflow: auto;
    }*/

        div#encounterPlansData div {
            display: block;
            text-align: left !important;
        }

        div#encounterPlansData .Link {
            color: blue;
        }



        /*div#encounterPlansData input:hover{
             color: #005cb9;
         }*/

        div#encounterPlansData span {
            vertical-align: top;
            color: black;
        }




    $(document).ready(function () {
        var $encisExcludeFromMU = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsExcludeFromMU_]&quot; , &quot;'&quot; , &quot;);
        if ($encisExcludeFromMU.length > 0) {
            if ($(&quot;#encMainDiv&quot;).length > 0) return;
            var fd_isExcludefromMU = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;);
            if (fd_isExcludefromMU.length > 0) {
                var $encisExcludeFromMU = ($encisExcludeFromMU.val() == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot; || $encisExcludeFromMU.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) ? true : false;
                if ($encisExcludeFromMU &amp;&amp; typeof fd_isExcludefromMU[0] != &quot;undefined&quot; &amp;&amp; !fd_isExcludefromMU[0].checked) {
                    if (!fd_isExcludefromMU[0].checked) {
                        fd_isExcludefromMU[0].checked = true;
                        onSimpleControlDataChange(fd_isExcludefromMU);
                    }
                    //if (!$encisExcludeFromMU[0].checked) {
                    //    $encisExcludeFromMU[0].checked = true;
                    //    onSimpleControlDataChange($encisExcludeFromMU);
                    //}
                }
                else if (!$encisExcludeFromMU &amp;&amp; typeof fd_isExcludefromMU[0] != &quot;undefined&quot; &amp;&amp; fd_isExcludefromMU[0].checked) {
                    if (fd_isExcludefromMU[0].checked) {
                        fd_isExcludefromMU[0].checked = false;
                        onSimpleControlDataChange(fd_isExcludefromMU);
                    }
                }
            }
        }
        if ($(&quot;#IsExcludeFromMU&quot;)[0] != undefined &amp;&amp; $(&quot;#IsExcludeFromMU&quot;)[0].checked == true) {
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;)[0].checked = true;
        }
        else if ($(&quot;#IsExcludeFromMU&quot;)[0] != undefined &amp;&amp; $(&quot;#IsExcludeFromMU&quot;)[0].checked == false) {
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;)[0].checked = false;
        }

        if ($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;patientencounterid&quot; , &quot;'&quot; , &quot;) != undefined) {
            if ($(&quot;#encMainDiv&quot;).length > 0) return;
            var $encisBaselineExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsBaseLineExam_]&quot; , &quot;'&quot; , &quot;);
            if ($encisBaselineExam.length > 0) {
                var fd_isBaselineExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;);
                var $isBaselineExam = ($encisBaselineExam.val() == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot; || $encisBaselineExam.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) ? true : false;
                if (fd_isBaselineExam) {
                    if (typeof fd_isBaselineExam[0] != &quot;undefined&quot; &amp;&amp; !fd_isBaselineExam[0].checked &amp;&amp; $encisBaselineExam.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) {
                        fd_isBaselineExam[0].checked = true;
                        onSimpleControlDataChange(fd_isBaselineExam);
                    }
                }
            }

            var $encisAnnualExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsAnnualExam_]&quot; , &quot;'&quot; , &quot;);
            if ($encisAnnualExam.length > 0) {
                var fd_isAnnualExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;);
                var $isAnnualexam = ($encisAnnualExam.val() == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot; || $encisAnnualExam.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) ? true : false;
                if (fd_isAnnualExam) {
                    if (($isAnnualexam) &amp;&amp; typeof fd_isAnnualExam[0] != &quot;undefined&quot; &amp;&amp; !fd_isAnnualExam[0].checked) {
                        fd_isAnnualExam[0].checked = true;
                        onSimpleControlDataChange(fd_isAnnualExam);
                    }
}


            }
            //}
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;).change(function (event) {
                onSimpleControlDataChange(this);
                var key = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;) + &quot;~S&quot; + &quot;~IsExcludeFromMU&quot;;
                var fieldValue = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;)[0].checked;
                var $current = $(&quot; , &quot;'&quot; , &quot;input[type=hidden][id^=IsExcludeFromMU]&quot; , &quot;'&quot; , &quot;);
                $(&quot;#IsExcludeFromMU&quot;)[0].checked = fieldValue;
                trackChanges(key, fieldValue, $current);
                //onclick of IsExcludeFromIP checkbox from Final Diagnosis element,it should change the value from hamburger menu also.
                if (fieldValue == true) {
                   $(&quot;.excludeCheck&quot;).addClass(&quot;mif-checkMark&quot;);
                }
                else {
                   $(&quot;.excludeCheck&quot;).removeClass(&quot;mif-checkMark&quot;);
                }
                currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;data-changed&quot; , &quot;'&quot; , &quot;, true);
            });

            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;).change(function (event) {
                var fd_isAnnualExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;);
                onSimpleControlDataChange(this);
                var key = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;) + &quot;~S&quot; + &quot;~IsBaseLineExam&quot;;
                var fieldValue = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;)[0].checked;
                var $current = $(&quot; , &quot;'&quot; , &quot;input[type=hidden][id^=IsBaseLineExam]&quot; , &quot;'&quot; , &quot;);
                trackChanges(key, fieldValue, $current);
                if (!checkValISNullUndefinedBlank(fieldValue))
                {
                    var $encFormBaselineExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsBaseLineExam_]&quot; , &quot;'&quot; , &quot;);
                    $encFormBaselineExam.val(fieldValue);
                }
                if (this.checked &amp;&amp; !fd_isAnnualExam[0].checked) {
                    //fd_isAnnualExam[0].checked = this.checked;
                    // onSimpleControlDataChange(fd_isAnnualExam);
                }

            });
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;).change(function (event) {
                var fd_isBaselineExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;);
                var fieldValue = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;)[0].checked;
                onSimpleControlDataChange(this);
                if (!checkValISNullUndefinedBlank(fieldValue)) {
                    var $encFormAnnualExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsAnnualExam_]&quot; , &quot;'&quot; , &quot;);
                    $encFormAnnualExam.val(fieldValue);
                }
                if (this.checked &amp;&amp; !fd_isBaselineExam[0].checked) {
                    //fd_isBaselineExam[0].checked = this.checked;
                    //onSimpleControlDataChange(fd_isBaselineExam);
                }
            });

        }
        if (&quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot; == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot; || &quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot; == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot;) {
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;IsBillCodeSeqChanged&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).show();
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).powerTip({ placement: &quot; , &quot;'&quot; , &quot;se&quot; , &quot;'&quot; , &quot;, mouseOnToPopup: true }).data(&quot; , &quot;'&quot; , &quot;powertip&quot; , &quot;'&quot; , &quot;, function () {
                var r = $(&quot;&lt;div class=&quot; , &quot;'&quot; , &quot;no-margin font14 pad05&quot; , &quot;'&quot; , &quot;>Diagnosis code order is changed, click refresh code on final procedure code&lt;/div>&quot;);
                return r;
            });
        } else {
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;IsBillCodeSeqChanged&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;false&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).hide();
        }

        plansSectionsForAnnualReason();
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });

    //var asHashKeyData;
    //var mainGridName;
    //var hash = {};

    //function ShowDEPopUp(gridName) {
    //    var asHashKey = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;);
    //    var title = &quot; , &quot;'&quot; , &quot;Final Diagnosis - Data Entry&quot; , &quot;'&quot; , &quot;;
    //    var callbackUrl = &quot; , &quot;'&quot; , &quot;FinalDiagnosisShowPopUp/ComplexElements&quot; , &quot;'&quot; , &quot;;
    //    var popUpSize = &quot; , &quot;'&quot; , &quot;popup-100&quot; , &quot;'&quot; , &quot;;
    //    var patientEncounterId = currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;patientEncounterId&quot; , &quot;'&quot; , &quot;);
    //    asHashKeyData = asHashKey + $(gridName.mainElement).find(&quot; , &quot;'&quot; , &quot;[name*=&quot;_GF_&quot;]&quot; , &quot;'&quot; , &quot;).closest(&quot; , &quot;'&quot; , &quot;div[data-sysdef]&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;data-askey&quot; , &quot;'&quot; , &quot;);
    //    mainGridName = gridName;
    //    hash = {};
    //    finalDiagonsisGridView_ShowPopUp(title, callbackUrl, popUpSize, gridName, patientEncounterId);
    //    uniqueElmInterName = {};
    //}




    
        
            
                
                        
                
                
                        
                
                
                        
                
                
                        

                
                
            
        
    
    

	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search Existing Final Diagnoses&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

				
			
		
			
				
					
						
					
						
							
								No. 
							
						
							
								Code 
							
						
							
								Description 
							
						
							
								Coding System 
							
						
							
								Actions 
							
						
							
								# 
							
						
					
				
			
		
			
				
					
				
					    
				
					
						No data to display
					
				
			
		
			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DIAG_POINTER_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_DESCRIPTION_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;CODING_SYSTEM_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;guYO4+k0S5jwy1tF/QoXin9jKJ7n7ykTCy4dMZxMd9fHKtzpRbLNKZ8aOp40PYwzMa4SFvnfbU2DRs9T2IHy5y8fgk0byRib9oTe8rud6PgWdGkG4L5tYvTPiI/n2sTl1RMn+JwVUVPnzYxKqdV/5JOnBD9ezhljH3+8ppdkXPuHIy4yvvWCmo1ZLV32lN/422XFEjtsCynatWQz2Pju6sz7lHInsaOlkMks/HiGUzMzXA2UhS2pkdIeAHSbCR/dVTSEKCUaDsAzkkb7uHL+Q/3jmDGHNrAZNaNoOSxOA24PyffcnwLLudTnDegaijm7hU+IkGQQzTxvDX4bj3Bjfh5aTDpcejqWSvZuq0IW06zqeu0jf1YOjkoP5tM6Xz0SR9awR3aq6Ty1zmV5OuwiRJ4pqjCi1XiG598ibt3TbZfuKtj11dA00r9eqMumQJg/50B/tqy2/89DDVMxLki7tSt+iILg0j6Gnbv8Vgo4q+eO9dFK+wNmcSjNK5C/Trza48jhtHyW9bFPqox93045PUWJm/k=&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_DIAG_POINTER&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;No.&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DIAGNOSIS_CODE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Code&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DIAGNOSIS_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Description&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_CODING_SYSTEM&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Coding System&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Actions&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_digcode_ptrs&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RecordId&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DIAG_POINTER&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;CODING_SYSTEM&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[5],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;DIAG_POINTER&quot; , &quot;'&quot; , &quot;,0,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE&quot; , &quot;'&quot; , &quot;,0,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;,0,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;CODING_SYSTEM&quot; , &quot;'&quot; , &quot;,0,,,,3],[5,,&quot; , &quot;'&quot; , &quot;No_Focus&quot; , &quot;'&quot; , &quot;,,0,,,,4,,,,,0]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/FinalDiagnosesGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=e65e&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:finalDiagnosesGridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:enc_grid_end_callback,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:finalDiagnosesGridView_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:gridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;FocusedCellChanging&quot; , &quot;'&quot; , &quot;:onFocusedCellChanging,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:finalDiagnosesGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;6&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->




    $(document).ready(function () {
        var rows = [];
            var rowKeys = Object.keys(rows)
            for (var r = 0; r &lt; rowKeys.length; r++) {
                complexElementOldData[rows[r].RowGuid] = rows[rowKeys[r]];
            }
            const $editableDiv = $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;,$(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;))
            function adjustHeightFromElement() {
                $editableDiv.css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;auto&quot; , &quot;'&quot; , &quot;);
                if($editableDiv[0].scrollHeight &lt; parseInt($(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;), 10)){
                    $editableDiv.css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;))
                }
                else
                $editableDiv.css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, $editableDiv[0].scrollHeight + &quot; , &quot;'&quot; , &quot;px&quot; , &quot;'&quot; , &quot;); // Set new height based on content
            }

            if (GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;) != null &amp;&amp; GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;).PreferenceValue == &quot;Y&quot;) {
                $editableDiv.on(&quot; , &quot;'&quot; , &quot;focus&quot; , &quot;'&quot; , &quot;, function () {
                    $(this).addClass(&quot; , &quot;'&quot; , &quot;expanded&quot; , &quot;'&quot; , &quot;);
                    adjustHeightFromElement();
                });
                $editableDiv.on(&quot; , &quot;'&quot; , &quot;blur&quot; , &quot;'&quot; , &quot;, function () {
                    $(this).removeClass(&quot; , &quot;'&quot; , &quot;expanded&quot; , &quot;'&quot; , &quot;);
                    $(this).css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;)); // Reset to original height
                });
                $editableDiv.on(&quot; , &quot;'&quot; , &quot;input&quot; , &quot;'&quot; , &quot;, function () {
                    adjustHeightFromElement();
                });
            }
    });
   

    

Final Procedures#1Add
    $(document).ready(function () {
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });





    
        
            
                
                    Select Code Bundle
EP Routine Exam + Refract + Optos
Glaucoma 99213 + Pachy,  VF, OCT of Optic nerve
NP Routine Exam + OPT + VF
NP Routine Exam + Refract + Optos
NP Routine Exam + Refract + Optos(on 2nd bill)

                
                
                    
                

                
                        
                
                
                            
                
                
                
                        

                
                
            
        
    

    


	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search Existing Final Procedures&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

				
			
		
			
				
					
						
					
						
							
								Bill To 
							
						
							
								Shortcut 
							
						
							
								Code 
							
						
							
								Description 
							
						
							
								Modifiers 
							
						
							
								Units 
							
						
							
								Diag. Ptrs. 
							
						
							
								Actions 
							
						
							
								# 
							
						
					
				
			
		
			
				
					
				
					       
				
					
						No data to display
					
				
			
		
			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;BILL_TO_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;SHORTCUT_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE_DESCRIPTION_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;MODIFIERS_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DX_POINTERS_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;NRqHpu6AS8T0kAvz3EscewJfHG4WMYCx69BlQqwshDh4+XIrVMx6Kpoc6FEQHE03hEblUQpdKGLpHyz9waPHkYRrcVU/CWd66hraFJl7yAN6T3rFQwqmgqqa4Jq/u4Dejy0lgbNGVOrIvhrGq9GdkcDapqPBziAfXD6e7fiUcpFwudBw9XTWyJCNg4rszYIZ+bTQlK9lPABJoxd3jS1HqWeOUIwXvbrgaHN2NIoe8pogKqGclnrK8Mzzup67EanROePDn0PmGVQsEDMyjm50DwWUgRUyIJxNKOiCKjKKv/ox7CL2AVqUbKl0WFuFX4z/GlQd1Dhc5NTFxZqBJ3Y11MctEK7lss3aGqXmuoyck3oSwWhP/PXUSjuibwiJ+yAWhfvg2nTFqA1J7OtTIJV6S7SpTP6Tz5DfEYMUW7YpQD4x46hM9PqG392LkdUS6XOFlXvwaIfC4uXt0i4BOnPfjn+39xTxWJEMmd/3gV2fDJVYWQy188AuvnRSYa99n3HFWJKy7D1ue+uEuS82uQJYK+Tg2tWGzK8m7s26lY/eXQu26/HJ15Jjsp3o9Qq82w64Fu3AeR1z9a7w4UygDCrhYy6NIqsfXyIaB4FsXVG0AGS+FQhU&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_BILL_TO&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Bill To&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_SHORTCUT&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Shortcut&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_PROCEDURE_CODE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Code&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_PROCEDURE_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Description&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_MODIFIERS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Modifiers&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_UNITS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Units&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DX_POINTERS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Diag. Ptrs.&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Actions&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5,6,7,8],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5,6,7,8],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[8],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;BILL_TO&quot; , &quot;'&quot; , &quot;,0,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;SHORTCUT&quot; , &quot;'&quot; , &quot;,0,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE&quot; , &quot;'&quot; , &quot;,0,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;,0,,,,3],[5,,,&quot; , &quot;'&quot; , &quot;MODIFIERS&quot; , &quot;'&quot; , &quot;,0,,,,4],[6,,,&quot; , &quot;'&quot; , &quot;UNITS&quot; , &quot;'&quot; , &quot;,0,,,,5],[7,,,&quot; , &quot;'&quot; , &quot;DX_POINTERS&quot; , &quot;'&quot; , &quot;,0,,,,6],[8,,&quot; , &quot;'&quot; , &quot;No_Focus&quot; , &quot;'&quot; , &quot;,,0,,,,7,,,,,0]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/FinalProceduresGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=de8b&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:finalProceduresGridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:enc_grid_end_callback,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:finalProceduresGridView_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:gridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;FocusedCellChanging&quot; , &quot;'&quot; , &quot;:onFocusedCellChanging,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:finalProceduresGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;9&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->

    


    .dxgvSearchPanel_Metropolis.Search_Final_Procedures table > tbody > tr > td:first-child > table {
    width: 73% !important;
}
Final Outbound Documents#1Add    
    $(document).ready(function () {
        setMaxlengthAttribute(true);
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });
  


    
        
            
                
                        
                
                
                    
                
                
                    
                
                
                    
                
                
                        
                
                
                        
                
                
                        
                
                
            
        
    
    

	
		
	
		
			
				
					
				
			
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

		
	

	
		
			
				
			
				
					
						Document 
					
				
					
						Recipients 
					
				
					
						Needs Review? 
					
				
					
						Reviewed? 
					
				
					
						Status 
					
				
					
						  
					
				
					
						
					
				
			
		
	

	
		
			
		
			No
		
			Cataract ConsultationOwvkmi L...NoSent
		
			
				No data to display
			
		
	

	

	
		
			
		
	

&lt;!--
var dxo = ASPx.GetControlCollection().Get(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;);
dxo.SetProperties({&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1d3962c1-4563-4d42-b32e-eedec39611de&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1d3962c1-4563-4d42-b32e-eedec39611de&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null},&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;selectAllBtnStateWithoutPage&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selectAllSettings&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;index&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;mode&quot; , &quot;'&quot; , &quot;:1}],&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;,,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;RECIPIENTS&quot; , &quot;'&quot; , &quot;,,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;,,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;IS_REVIEWED&quot; , &quot;'&quot; , &quot;,,,,,3],[5,,,&quot; , &quot;'&quot; , &quot;STATUS&quot; , &quot;'&quot; , &quot;,,,,,4],[6,,,,0,,,,5,,,,,0],[7,0,,&quot; , &quot;'&quot; , &quot;DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;,,,,,,,,,,,,,1]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1});

//-->

&lt;!--
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis EmptyFocusedRow&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->

	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;focusedRow&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;gtQ5mScUQa42i15C4I6ODTbJhK7cGLWw2bDTsA1JZwnnJjHFTsx/t62d5iELNbjcmOAusn/Tx6HnJGT6zVnFS+AiQeMKsKjY4fs/kbs8N/x4aYRBa1yt60qi4301RagldAulQCOdIBe/TMr7y6Cs6oE8hqQ2IhjAfIcAhllDHn9FF4DVtZtJ2hfD0W9tOBRf5EWY9U7O91AQWasJj7YmzNydxialp2lXbzjDr8y1E4qK8YqB//qFicuGshMXVX7pU5N5G2z9B3YwN7R/EUH58T1iy7BC0MDDfO3CTShV4heQmzLB59/B94/lJCHvMxCr87BRCu/GZB8bzpjEjfwW4SqxVKg7T9Lvs0lPumZbmUJUjd6APGqrDdA2g2iNrDaHsG5WoL+S+3kfmdBHVhkywMeCeEpzHGnA2kHEbSAxZYdDPyFDEC8g3B7G7k3bj+7SNkrV+WD2lxSeZ2mU0unFhPUbzMk4Tw8BGWxfPa8qpNzwjHl+rUw6xogMI0/C3n3f/SsPESyJXLjY6mMQOxduu2p/ib8i0JQ2DkKugza4fge5TYgQoGHQwZaAsfqNpRskQfqgOLG45aSsWGAT6YV/LmYAgb8=&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Document&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_RECIPIENTS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Recipients&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Needs Review?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_IS_REVIEWED&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Reviewed?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_STATUS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Status&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RecordId&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;195&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;STATUS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Not Sent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Complete Eye Exam Report Brief&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RECIPIENTS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Owvkmi Lomjftuh(Patient)&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;IS_REVIEWED&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot;}},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;195&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null},&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;selectAllBtnStateWithoutPage&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selectAllSettings&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;index&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;mode&quot; , &quot;'&quot; , &quot;:1}],&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;,,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;RECIPIENTS&quot; , &quot;'&quot; , &quot;,,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;,,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;IS_REVIEWED&quot; , &quot;'&quot; , &quot;,,,,,3],[5,,,&quot; , &quot;'&quot; , &quot;STATUS&quot; , &quot;'&quot; , &quot;,,,,,4],[6,,,,0,,,,5,,,,,0],[7,0,,&quot; , &quot;'&quot; , &quot;DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;,,,,,,,,,,,,,1]],&quot; , &quot;'&quot; , &quot;pendingEvents&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;RaiseFocusedItemChangedOutOfServer&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/FinalOutboundDocumentGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=d762&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:finalOutboundDocumentgridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:enc_grid_end_callback,&quot; , &quot;'&quot; , &quot;SelectionChanged&quot; , &quot;'&quot; , &quot;:onCheckChangeFOD,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:gridViewFOD_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:gridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:finalOutboundDocumentgridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis EmptyFocusedRow&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->


    


    .EmptyFocusedRow, .EmptyFocusedRow td {
        background: none !important;
    }


    $(document).ready(function () {
        //$(&quot; , &quot;'&quot; , &quot;.fodTitle&quot; , &quot;'&quot; , &quot;).powerTip({ placement: &quot; , &quot;'&quot; , &quot;sw&quot; , &quot;'&quot; , &quot;, mouseOnToPopup: true }).data(&quot; , &quot;'&quot; , &quot;powertip&quot; , &quot;'&quot; , &quot;, function () {
        //    var r = $(&quot;&lt;p class=&quot; , &quot;'&quot; , &quot;no-margin&quot; , &quot;'&quot; , &quot;> &quot; , &quot;'&quot; , &quot;&quot; + $(this).attr(&quot;data-title&quot;) + &quot;&quot; , &quot;'&quot; , &quot;&lt;/p>&quot;);
        //    return r;
        //});
    });

Amendments#1Add
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
        setMaxlengthAttribute(true);
        if (!($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;) == &quot;True&quot;)) {
            $(&quot;#SpPlusIcon&quot;).css(&quot;display&quot;,&quot;none&quot;);
        }
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });
   



  
    
        
            
                
                        
                
                
                    
                
                
                        
                
                
            
        
    
    

	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search Existing Amendments&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

				
			
		
			
				
					
						
					
						
							
								Date 
							
						
							
								Notes 
							
						
							
								Status 
							
						
							
								Source 
							
						
							
								Created By 
							
						
					
				
			
		
			
				
					
				
					
						No data to display
					
				
			
		
			
				
					
						
							Expand All
						
							Collapse All
						
							Sort Ascending
						
							Sort Descending
						
							Clear Sorting
						
							Group By This Column
						
							Ungroup
						
							Group Panel
						
							Show Column
						
							Hide Column
						
							Show Customization Dialog
						
							Column Chooser
						
							Clear Filter
						
							Search Panel
						
							Filter Builder...
						
							Filter Row
						
							Filter Row Menu
						
							Footer
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI9_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI10_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI11_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI12_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI13_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI14_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI15_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI16_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI17_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowCustDialogHover_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[null],[null],[null],[null],[null],[null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddSelectedItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-checked&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI11_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI13_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI15_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI16_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI17_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[null],[null],[null],[null],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxWeb_mSubMenuItemChecked_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxWeb_mSubMenuItemChecked_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxWeb_mSubMenuItemChecked_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI9_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI10_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI11_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI12_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI13_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI14_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI15_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI16_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI17_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMFullExpandDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMFullCollapseDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSortAscendingDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSortDescendingDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMGroupByColumnDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMGroupByColumnDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowGroupPanelDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowCustDialogDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowCustomizationWindowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMClearFilterDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowSearchPanelDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowFilterEditorDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[null],[null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG$DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullExpand&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullCollapse&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortAscending&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortDescending&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearSorting&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupByColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;UngroupColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowGroupPanel&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;9&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;HideColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;10&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationDialog&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;11&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationWindow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;12&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearFilter&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;13&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowSearchPanel&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;14&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterEditor&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;15&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;16&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRowMenu&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;17&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFooter&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;9&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;10&quot; , &quot;'&quot; , &quot;:[[1],[0],[1]],&quot; , &quot;'&quot; , &quot;11&quot; , &quot;'&quot; , &quot;:[[1],[0],[1]],&quot; , &quot;'&quot; , &quot;12&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;13&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[0,[-1]]],&quot; , &quot;'&quot; , &quot;14&quot; , &quot;'&quot; , &quot;:[[1],[0],[1]],&quot; , &quot;'&quot; , &quot;15&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;16&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;17&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0],[1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12],[13],[14],[15],[16],[17]]},&quot; , &quot;'&quot; , &quot;allowCheckItems&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;checkedState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;itemCheckedGroups&quot; , &quot;'&quot; , &quot;:[[&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;11&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;13&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;15&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;16&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;17&quot; , &quot;'&quot; , &quot;]],&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullExpand&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullCollapse&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortAscending&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortDescending&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearSorting&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupByColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;UngroupColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowGroupPanel&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;HideColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationDialog&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationWindow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearFilter&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowSearchPanel&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterEditor&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRowMenu&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFooter&quot; , &quot;'&quot; , &quot;}]});

//-->

			
				
					
						
							Expand
						
							Collapse
						
							Expand Detail
						
							Collapse Detail
						
							New
						
							Edit
						
							Delete
						
							Group Summary
						
							
						
							Refresh
						
					
				
			
				
					
						
							Sum
						
							Min
						
							Max
						
							Count
						
							Average
						
							None
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXME7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXMBC7_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI7i0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i5_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddSelectedItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-checked&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI7i0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i4_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMExpandRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMCollapseRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMExpandDetailRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMCollapseDetailRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMNewRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMEditRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMDeleteRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[null,null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMRefreshDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI7i0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i5_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummarySumDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryMinDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryMaxDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryCountDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryAverageDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG$DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandDetailRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseDetailRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;NewRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;EditRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;DeleteRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMenu&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummarySum&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMin&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMax&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i3&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryCount&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryAverage&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i5&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryNone&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Refresh&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i0&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i1&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i2&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i3&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i4&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i5&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0],[1],[2],[3],[4],[5],[6],[7],[8]],&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:[[0],[1],[2],[3],[4],[5]]},&quot; , &quot;'&quot; , &quot;allowCheckItems&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;checkedState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;itemCheckedGroups&quot; , &quot;'&quot; , &quot;:[[&quot; , &quot;'&quot; , &quot;7i0&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i1&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i2&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i3&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i4&quot; , &quot;'&quot; , &quot;]],&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandDetailRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseDetailRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;NewRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;EditRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;DeleteRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummarySum&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMin&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMax&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryCount&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryAverage&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryNone&quot; , &quot;'&quot; , &quot;}],&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMenu&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Refresh&quot; , &quot;'&quot; , &quot;}]});

//-->

	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/QEABH7hZeGGM/Qm5RGbMVYxTwBxn9Hc7T8sAKhydrFw1TSSwHy6kfsIueNOYirKhaTX/hQl8AzjYyTfiblVmfRaDBZBERdjpW3lPA7hfffImcgMXZSvDnaITPRqrq1Wz9qzrQbvjmjGJYzQLj6HSOdm6t/LB6s6qi2rFGVoM55YzGOCPsBhwhelCW9rqfj9VORWD7TVItklXYfDCEbvKWmQsxiEh33bOQB+e3O5O9Y90EABiege243j79eQTx/fMrZNhykjBKjWUcBRxwFHy/jdDyze83YTC6YHdD0HU3M7599bEfzA5N+fr+ag7c0fQgXnAKAVOTQ6I/zA79ePnV8Gf0+YJh8PKOPKulB3lIE5LJ5JH92jICGt3kZPpi5dbw6e2F0iZuuigtM4ITzanL1rqehooLZA44LplSmfZV47kYsEkhmYTUn1ImwU1CIPHnuHa2LwoN5ibiayNtMXWoQRDxKMTamnm2BfL2B+A81ONv1+ybTN7fAyUQr68O2mx9y+LA==&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cp_CREATED_DATE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Date&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_NOTES&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Notes&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_ADDENDUM_STATUS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Status&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_SOURCE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Source&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_CREATED_BY&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Created By&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,&quot; , &quot;'&quot; , &quot;CREATED_DATE&quot; , &quot;'&quot; , &quot;,,3,,,0],[1,,,&quot; , &quot;'&quot; , &quot;NOTES&quot; , &quot;'&quot; , &quot;,,,,,1],[2,,,&quot; , &quot;'&quot; , &quot;ADDENDUM_STATUS&quot; , &quot;'&quot; , &quot;,,,,,2],[3,,,&quot; , &quot;'&quot; , &quot;SOURCE&quot; , &quot;'&quot; , &quot;,,,,,3],[4,,,&quot; , &quot;'&quot; , &quot;CREATED_BY&quot; , &quot;'&quot; , &quot;,,,,,4]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/AddendumGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=7294&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:addendumGridView_Init,&quot; , &quot;'&quot; , &quot;ContextMenuItemClick&quot; , &quot;'&quot; , &quot;:encGrid_OnContextMenuItemClick});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;5&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->


    


   div[data-elementinternalname=Addendums] .dxgvSearchPanel_Metropolis > table {
        width: 35%;
    }
   div[data-elementinternalname=Addendums] .dxgvSearchPanel_Metropolis.dxgvSearchPanel_Metropolis_Right_new > table {
        width: 40%;
    }
Signatures#1Add
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });





    
        
            
                
                        
                
                
                        
                
                
                        
                    
                
                
            
        
    
    


	
		
			
				
					
						
					
						
							
								Type 
							
						
							
								User 
							
						
							
								Active? 
							
						
							
								Signed? 
							
						
							
								Sign 
							
						
							
								  
							
						
							
								
							
						
					
				
			
		
			
				
					
				
					ProviderPatient PortalYesNo
				
					
						No data to display
					
				
					  NoNo
				
			
		
			
				
					
						
							Edit Value List
						
							
						
					
				
			
				
					
						
							User Types
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXME0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXMBC0_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG$DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0]],&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0]]},&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;editVLSimple_User_Types&quot; , &quot;'&quot; , &quot;}],&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;gv_editValueList&quot; , &quot;'&quot; , &quot;}]});

//-->

			
				
					
						
							Edit Value List
						
							
						
					
				
			
				
					
						
							User Types
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXME0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXMBC0_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG$DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0]],&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0]]},&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;editVLSimple_User_Types&quot; , &quot;'&quot; , &quot;}],&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;gv_editValueList&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

	
		
			
				
					
						
							
								
									 
								
							

							
						
					
				
&lt;!--
ASPx.createControl(MVCxClientListBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF_DDD_L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF$DDD$L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;CustomCallback&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;isSyncEnabled&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;isComboBoxList&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hasSampleItem&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hoverClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemHover_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;selectedClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemSelected_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;disabledClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;itemsInfo&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Nurse&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Nurse&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PA&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;PA&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Tech&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Tech&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Other Staff&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Other Staff&quot; , &quot;'&quot; , &quot;]}]},{&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBSelectedIndexChanged(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); },&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBItemMouseUp(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			
		
	

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxpc-closeBtnHover&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;HCB-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupControl,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF$DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;adjustInnerControlsSizeOnShow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;popupAnimationType&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;slide&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;closeAction&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CloseButton&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupHorizontalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;LeftSides&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupVerticalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Below&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Shown&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.DDBPCShown(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }});

//-->

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemovePressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtDropDownDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],]]);
ASPx.createControl(MVCxClientComboBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;autoCompleteAttribute&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;autocomplete&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;off&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;incrementalFilteringMode&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;None&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;lastSuccessValue&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;islastSuccessValueInit&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;GotFocus&quot; , &quot;'&quot; , &quot;:ComboBox_ShowDropDown,&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown,&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:function(s, e) { GetDXControlsByName(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;).PerformCallback();}},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			


	
		Loading…
	



	
		
	

	
		
			
				
					
						
							
								
									 
								
							

							
								

							
						
					
				
&lt;!--
ASPx.createControl(MVCxClientListBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF_DDD_L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF$DDD$L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;CustomCallback&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;isSyncEnabled&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;isComboBoxList&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hasSampleItem&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;isCallbackMode&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;callbackPageSize&quot; , &quot;'&quot; , &quot;:100,&quot; , &quot;'&quot; , &quot;isHasFakeRow&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hoverClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemHover_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;selectedClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemSelected_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;disabledClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;itemsInfo&quot; , &quot;'&quot; , &quot;:[]},{&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBSelectedIndexChanged(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); },&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBItemMouseUp(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			
		
	

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxpc-closeBtnHover&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;HCB-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupControl,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF$DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;adjustInnerControlsSizeOnShow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;popupAnimationType&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;slide&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;closeAction&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CloseButton&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupHorizontalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;LeftSides&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupVerticalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Below&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Shown&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.DDBPCShown(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }});

//-->

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemovePressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtDropDownDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],]]);
ASPx.createControl(MVCxClientComboBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;autoCompleteAttribute&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;autocomplete&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;off&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;incrementalFilteringMode&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;None&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;isCallbackMode&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;lastSuccessValue&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;islastSuccessValueInit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/GetUsersByType&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:function(s, e) {e.customArgs[&quot; , &quot;'&quot; , &quot;dropDownName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;cascadeDropDownName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;keyDownEventName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;signature_KeyDown&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;lostFocusEventName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;signature_LostFocus&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;valueListInternaName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;fieldName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;cascadeFieldName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;cascadeFieldValue&quot; , &quot;'&quot; , &quot;] = GetDXControlsByName(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;).GetValue();},&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:SignatureonComboBoxSelectedIndexChanged},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;IS_ACTIVE_USER_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;readOnly&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;HAS_SIGNED_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;readOnly&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;No&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;UBT9G8BJrzDLM5gbBxe1NQfB9MUk9RxWZ1zoxlDhNQCEpNfEGNmJgG9t3XYCiwuxHcIWwdP45in5jj1i4gQJKrECx/p8iuMj8NZ/qosVWBew7k+k3dpoiKDXAsDQO/vcLXH21OVat+/BRxG+l/05p+KSa3LZ6XwHHg/zkxZlg5jfK2RDrkbK9HTh4taSjftpdDjtqPvYpdcmJqxlLQdu+f8G3ArjbV8B7LVvx2+9ZgJCIFknghwSr8XPIVpsxdG/YSzeNGSKpcotCWnMRfZrMoY4PR/s+MfOGS3LIBeeOYN/bTeYVh1i1ktS69YC+GN6C4wB8cZ3ntrk9XhNN+wTpasjhuaMtGRxq+mRpFRypdiokH/CE/hyTYJ0UvXNGHOZW0FCfE6osOJo3Zq6muHN4gQRPkp4V5pZZIF+pRHyZDtYVsEe5MfmM+uteZTN53dp/lvrKIvEgcHpWSzmcYYCdeyY6NPEICp7kF205gsINQ199rYG/w3hdS9JE7r3fCaUu71Y3MFJhpeW/OGx1HUnvLP1Dh3LhK7FAZzSzl+J1toi9VuuE8nZkuZNIZya0X3rwKk5FQ==&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Type&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;User&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_IS_ACTIVE_USER&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Active?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_HAS_SIGNED&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Signed?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_SIGNATURE_DATE_TIME&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Date &amp; Time&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RecordId&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;230&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Patient Portal&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;IS_ACTIVE_USER&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;HAS_SIGNED&quot; , &quot;'&quot; , &quot;:false}},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;230:12&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,6,7],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,6,7],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;No&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[3,4,6,7],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Patient Portal&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null},&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;selectAllBtnStateWithoutPage&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selectAllSettings&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;index&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;mode&quot; , &quot;'&quot; , &quot;:1}],&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;,,,,,0],[2,,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_NAME&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;,,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;IS_ACTIVE_USER&quot; , &quot;'&quot; , &quot;,,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;HAS_SIGNED&quot; , &quot;'&quot; , &quot;,,,,,3],[5,0,,&quot; , &quot;'&quot; , &quot;SIGNATURE_DATE_TIME&quot; , &quot;'&quot; , &quot;,,,,,,,,,,,,,1],[6,,,,0,,,,4,,,,,0],[7,,&quot; , &quot;'&quot; , &quot;No_Focus&quot; , &quot;'&quot; , &quot;,,0,,,,5,,,,,0]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/SignatureGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=f659&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:signatureGridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:signature_grid_end_callback,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:signatureGridView_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:signatureGridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;FocusedCellChanging&quot; , &quot;'&quot; , &quot;:onFocusedCellChanging,&quot; , &quot;'&quot; , &quot;ContextMenuItemClick&quot; , &quot;'&quot; , &quot;:encGrid_OnContextMenuItemClick,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:signatureGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc2&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis wm-hide dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc2&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis wm-hide dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->


    $(&quot; , &quot;'&quot; , &quot;.tooltip_sign_off_btn_elem&quot; , &quot;'&quot; , &quot;).powerTip({ placement: &quot; , &quot;'&quot; , &quot;s&quot; , &quot;'&quot; , &quot;, mouseOnToPopup: true }).data(&quot; , &quot;'&quot; , &quot;powertip&quot; , &quot;'&quot; , &quot;, function () {
        debugger
        if ($(this).attr(&quot; , &quot;'&quot; , &quot;data-enc-sign-off-date&quot; , &quot;'&quot; , &quot;) != undefined) {
            var r = $(&quot;&lt;div class=&quot; , &quot;'&quot; , &quot;no-margin font14 pad15&quot; , &quot;'&quot; , &quot;>&lt;div class=&quot; , &quot;'&quot; , &quot;float-left align-right&quot; , &quot;'&quot; , &quot;>&lt;b>Signed: &quot; + $(this).attr(&quot; , &quot;'&quot; , &quot;data-enc-sign-off-date&quot; , &quot;'&quot; , &quot;) + &quot;&lt;/b>&lt;/div>&quot;);
            return r;
        }
        else {
            return &quot;&quot;;
        }
        });


    



&quot;) or . = concat(&quot;Final Diagnoses#1AddAssessmentsPlansExclude from IPBaseline ExamAnnual exam
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
    });




    
    



    //0049539: Data Loss - Final RX data in Encounters and notes on Patient records disappeared
    var planDivLength = $(&quot; , &quot;'&quot; , &quot;div#encounterPlansData&quot; , &quot;'&quot; , &quot;).length;
    if (planDivLength > 1) {
        var formPageRelId = $(&quot; , &quot;'&quot; , &quot;[id^=encTabList_]&quot; , &quot;'&quot; , &quot;).closest(&quot; , &quot;'&quot; , &quot;.active-toggle&quot; , &quot;'&quot; , &quot;).parent().attr(&quot; , &quot;'&quot; , &quot;data-formpagerelid&quot; , &quot;'&quot; , &quot;);
        if (!checknullBlankUndefind(formPageRelId) &amp;&amp; checknullBlankUndefind($(&quot; , &quot;'&quot; , &quot;#IsOnEncFormScreen&quot; , &quot;'&quot; , &quot;).val())) {
            for (var i = 0; i &lt; planDivLength; i++) {
                var findClosestDivForId = $(&quot; , &quot;'&quot; , &quot;div#encounterPlansData&quot; , &quot;'&quot; , &quot;)[i].closest(&quot; , &quot;'&quot; , &quot;[id^=encformbase]&quot; , &quot;'&quot; , &quot;);
                if (!checknullBlankUndefind(findClosestDivForId)) {
                    var otherPageRelId = $(findClosestDivForId).attr(&quot; , &quot;'&quot; , &quot;data-formpagerelid&quot; , &quot;'&quot; , &quot;);
                    if (!checknullBlankUndefind(otherPageRelId) &amp;&amp; otherPageRelId != formPageRelId) {
                        var $finalDiagnosesDivLength = $(&quot;div[data-formpagerelid=&quot; + otherPageRelId + &quot;]&quot;).find(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;&quot; + &quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot; + &quot;&quot; , &quot;'&quot; , &quot;]&quot;);
                        if ($finalDiagnosesDivLength.length > 0) {
                            $finalDiagnosesDivLength.remove();
                        }
                        break;
                    }
                }
            }
        }
    }
    $(document).ready(function () {
        if (GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;) != null &amp;&amp; GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;).PreferenceValue == &quot;Y&quot;) {
            $(&quot; , &quot;'&quot; , &quot;textarea[name*=&quot;Final_Diagnoses.ASSESSMENT&quot;]&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;overflow&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;hidden&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;overflow&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;hidden&quot; , &quot;'&quot; , &quot;);
        }
        var planHt = $(&quot;div[data-ec_ctrldispname=&quot; , &quot;'&quot; , &quot;PLAN_HTML&quot; , &quot;'&quot; , &quot;]&quot;).height();
        $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, planHt + &quot; , &quot;'&quot; , &quot;px&quot; , &quot;'&quot; , &quot;);
        //Due to https://h00ghi19.maximeyes.com/ URL issue - data showing in new line so we changed below code
        var PlanLocalData = localStorage.getItem(&quot;PlanHTML&quot;);
        if (PlanLocalData != &quot;&quot; &amp;&amp; PlanLocalData != null) {
            var planHtmlData = $.parseHTML(PlanLocalData);
            checkLocaStorageforSetData(&quot;PlanHTML&quot;, &quot;&quot;);
        }
        else {
            var planHtmlData = ``; //MBT #35015, #35014 when plan data has &quot;`&quot; and &quot;&quot; , &quot;'&quot; , &quot;&quot;
        }

        if (planHtmlData != &quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;) $(&quot; , &quot;'&quot; , &quot;div[name*=PLAN_HTML]&quot; , &quot;'&quot; , &quot;).find(&quot; , &quot;'&quot; , &quot;div[id^=encounterPlansData]&quot; , &quot;'&quot; , &quot;).html(planHtmlData).trigger(&quot; , &quot;'&quot; , &quot;change&quot; , &quot;'&quot; , &quot;);
        //$(&quot; , &quot;'&quot; , &quot;textarea[name*=PLAN]&quot; , &quot;'&quot; , &quot;).css(&quot;visibility&quot;, &quot;hidden&quot;);
        $(&quot; , &quot;'&quot; , &quot;textarea[name*=PLAN][data-elementname=&quot;Final_Diagnoses&quot;]&quot; , &quot;'&quot; , &quot;).css(&quot;visibility&quot;, &quot;hidden&quot;);
    });

    if (($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;) != undefined &amp;&amp; $(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;).toLowerCase() == &quot;true&quot;)|| ($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;iseditable&quot; , &quot;'&quot; , &quot;) != undefined &amp;&amp; $(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;iseditable&quot; , &quot;'&quot; , &quot;).toLowerCase() == &quot;false&quot;)) {
        $(&quot;#encounterPlansData&quot;).bind(&quot;keydown&quot;, function (e) {
            e.preventDefault();
            return;
        });
    }


    //function CheckEncounterLockStat(e) {
    //    if ($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;).toLowerCase() == &quot;true&quot;) {
    //       // showConfirmationBar(&quot;Encounter Locked!&quot;, &quot;The selected encounter is locked. Do you want to add an amendment for this encounter?&quot;, &quot;OK&quot;, &quot;Cancel&quot;, &quot;&quot;);
    //        e.textContent = &quot;&quot;;
    //       // $(&quot;#OK&quot;).unbind(&quot; , &quot;'&quot; , &quot;click&quot; , &quot;'&quot; , &quot;);
    //        //$(&quot;#OK&quot;).bind(&quot; , &quot;'&quot; , &quot;click&quot; , &quot;'&quot; , &quot;, function () {
    //        //    e.textContent = &quot;&quot;;
    //        //    ShowModalPopup(&quot; , &quot;'&quot; , &quot;Patient Encounter Amendment&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;popup-30&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;GetAddendumNotes/PatientRecord&quot; , &quot;'&quot; , &quot;, null, null);
    //        //});
    //        //e.textContent = &quot;&quot;;
    //        //$(&quot;#Cancel&quot;).unbind(&quot; , &quot;'&quot; , &quot;Cancel&quot; , &quot;'&quot; , &quot;);
    //        //$(&quot;#Cancel&quot;).bind(&quot; , &quot;'&quot; , &quot;click&quot; , &quot;'&quot; , &quot;, function () {
    //        //    e.textContent = &quot;&quot;;
    //        //});
    //    }
    //}

    $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).bind(&quot; , &quot;'&quot; , &quot;blur&quot; , &quot;'&quot; , &quot;, function () {
        var plansData = getPlanText(&quot;#encounterPlansData&quot;, true);
        plansData = $(&quot; , &quot;'&quot; , &quot;&lt;div>&quot; , &quot;'&quot; , &quot; + plansData + &quot; , &quot;'&quot; , &quot;&lt;/div>&quot; , &quot;'&quot; , &quot;)[0].textContent;
        $(&quot; , &quot;'&quot; , &quot;textarea[name*=PLAN][data-elementname=&quot;Final_Diagnoses&quot;]&quot; , &quot;'&quot; , &quot;).val(plansData).trigger(&quot; , &quot;'&quot; , &quot;change&quot; , &quot;'&quot; , &quot;);
        submitChanges(false);

        var planHtmlElement = $(&quot; , &quot;'&quot; , &quot;div[name*=PLAN_HTML]&quot; , &quot;'&quot; , &quot;);
        if (currentActivatedForm != false) {
            var asHashKey = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;);
            asHashKey = asHashKey + &quot;~S~F_F~Final_Diagnoses.PLAN_HTML&quot;;
            var data = $.trim($(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).html());
            trackChanges(asHashKey, data, planHtmlElement);
            submitChanges(false);
        }

        // Save Child Record
        savePlansChildRecord(&quot;#encounterPlansData&quot;);
    });


    /*div#encounterPlansData {
        overflow: auto;
    }*/

        div#encounterPlansData div {
            display: block;
            text-align: left !important;
        }

        div#encounterPlansData .Link {
            color: blue;
        }



        /*div#encounterPlansData input:hover{
             color: #005cb9;
         }*/

        div#encounterPlansData span {
            vertical-align: top;
            color: black;
        }




    $(document).ready(function () {
        var $encisExcludeFromMU = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsExcludeFromMU_]&quot; , &quot;'&quot; , &quot;);
        if ($encisExcludeFromMU.length > 0) {
            if ($(&quot;#encMainDiv&quot;).length > 0) return;
            var fd_isExcludefromMU = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;);
            if (fd_isExcludefromMU.length > 0) {
                var $encisExcludeFromMU = ($encisExcludeFromMU.val() == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot; || $encisExcludeFromMU.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) ? true : false;
                if ($encisExcludeFromMU &amp;&amp; typeof fd_isExcludefromMU[0] != &quot;undefined&quot; &amp;&amp; !fd_isExcludefromMU[0].checked) {
                    if (!fd_isExcludefromMU[0].checked) {
                        fd_isExcludefromMU[0].checked = true;
                        onSimpleControlDataChange(fd_isExcludefromMU);
                    }
                    //if (!$encisExcludeFromMU[0].checked) {
                    //    $encisExcludeFromMU[0].checked = true;
                    //    onSimpleControlDataChange($encisExcludeFromMU);
                    //}
                }
                else if (!$encisExcludeFromMU &amp;&amp; typeof fd_isExcludefromMU[0] != &quot;undefined&quot; &amp;&amp; fd_isExcludefromMU[0].checked) {
                    if (fd_isExcludefromMU[0].checked) {
                        fd_isExcludefromMU[0].checked = false;
                        onSimpleControlDataChange(fd_isExcludefromMU);
                    }
                }
            }
        }
        if ($(&quot;#IsExcludeFromMU&quot;)[0] != undefined &amp;&amp; $(&quot;#IsExcludeFromMU&quot;)[0].checked == true) {
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;)[0].checked = true;
        }
        else if ($(&quot;#IsExcludeFromMU&quot;)[0] != undefined &amp;&amp; $(&quot;#IsExcludeFromMU&quot;)[0].checked == false) {
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;)[0].checked = false;
        }

        if ($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;patientencounterid&quot; , &quot;'&quot; , &quot;) != undefined) {
            if ($(&quot;#encMainDiv&quot;).length > 0) return;
            var $encisBaselineExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsBaseLineExam_]&quot; , &quot;'&quot; , &quot;);
            if ($encisBaselineExam.length > 0) {
                var fd_isBaselineExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;);
                var $isBaselineExam = ($encisBaselineExam.val() == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot; || $encisBaselineExam.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) ? true : false;
                if (fd_isBaselineExam) {
                    if (typeof fd_isBaselineExam[0] != &quot;undefined&quot; &amp;&amp; !fd_isBaselineExam[0].checked &amp;&amp; $encisBaselineExam.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) {
                        fd_isBaselineExam[0].checked = true;
                        onSimpleControlDataChange(fd_isBaselineExam);
                    }
                }
            }

            var $encisAnnualExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsAnnualExam_]&quot; , &quot;'&quot; , &quot;);
            if ($encisAnnualExam.length > 0) {
                var fd_isAnnualExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;);
                var $isAnnualexam = ($encisAnnualExam.val() == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot; || $encisAnnualExam.val() == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;) ? true : false;
                if (fd_isAnnualExam) {
                    if (($isAnnualexam) &amp;&amp; typeof fd_isAnnualExam[0] != &quot;undefined&quot; &amp;&amp; !fd_isAnnualExam[0].checked) {
                        fd_isAnnualExam[0].checked = true;
                        onSimpleControlDataChange(fd_isAnnualExam);
                    }
}


            }
            //}
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;).change(function (event) {
                onSimpleControlDataChange(this);
                var key = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;) + &quot;~S&quot; + &quot;~IsExcludeFromMU&quot;;
                var fieldValue = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ENCOUNTER_EXCLUDED_FROM_ARRA_CALCULATIONS]&quot; , &quot;'&quot; , &quot;)[0].checked;
                var $current = $(&quot; , &quot;'&quot; , &quot;input[type=hidden][id^=IsExcludeFromMU]&quot; , &quot;'&quot; , &quot;);
                $(&quot;#IsExcludeFromMU&quot;)[0].checked = fieldValue;
                trackChanges(key, fieldValue, $current);
                //onclick of IsExcludeFromIP checkbox from Final Diagnosis element,it should change the value from hamburger menu also.
                if (fieldValue == true) {
                   $(&quot;.excludeCheck&quot;).addClass(&quot;mif-checkMark&quot;);
                }
                else {
                   $(&quot;.excludeCheck&quot;).removeClass(&quot;mif-checkMark&quot;);
                }
                currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;data-changed&quot; , &quot;'&quot; , &quot;, true);
            });

            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;).change(function (event) {
                var fd_isAnnualExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;);
                onSimpleControlDataChange(this);
                var key = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;) + &quot;~S&quot; + &quot;~IsBaseLineExam&quot;;
                var fieldValue = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;)[0].checked;
                var $current = $(&quot; , &quot;'&quot; , &quot;input[type=hidden][id^=IsBaseLineExam]&quot; , &quot;'&quot; , &quot;);
                trackChanges(key, fieldValue, $current);
                if (!checkValISNullUndefinedBlank(fieldValue))
                {
                    var $encFormBaselineExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsBaseLineExam_]&quot; , &quot;'&quot; , &quot;);
                    $encFormBaselineExam.val(fieldValue);
                }
                if (this.checked &amp;&amp; !fd_isAnnualExam[0].checked) {
                    //fd_isAnnualExam[0].checked = this.checked;
                    // onSimpleControlDataChange(fd_isAnnualExam);
                }

            });
            $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;).change(function (event) {
                var fd_isBaselineExam = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_BASELINE_EXAM]&quot; , &quot;'&quot; , &quot;);
                var fieldValue = $(&quot;div[data-elementinternalname=&quot; , &quot;'&quot; , &quot;Final_Diagnoses&quot; , &quot;'&quot; , &quot;]&quot;).find(&quot; , &quot;'&quot; , &quot;input[name*=IS_ANNUAL_EXAM]&quot; , &quot;'&quot; , &quot;)[0].checked;
                onSimpleControlDataChange(this);
                if (!checkValISNullUndefinedBlank(fieldValue)) {
                    var $encFormAnnualExam = $(currentActivatedForm).find(&quot; , &quot;'&quot; , &quot;input[type=hidden][id ^=IsAnnualExam_]&quot; , &quot;'&quot; , &quot;);
                    $encFormAnnualExam.val(fieldValue);
                }
                if (this.checked &amp;&amp; !fd_isBaselineExam[0].checked) {
                    //fd_isBaselineExam[0].checked = this.checked;
                    //onSimpleControlDataChange(fd_isBaselineExam);
                }
            });

        }
        if (&quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot; == &quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot; || &quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot; == &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot;) {
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;IsBillCodeSeqChanged&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;true&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).show();
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).powerTip({ placement: &quot; , &quot;'&quot; , &quot;se&quot; , &quot;'&quot; , &quot;, mouseOnToPopup: true }).data(&quot; , &quot;'&quot; , &quot;powertip&quot; , &quot;'&quot; , &quot;, function () {
                var r = $(&quot;&lt;div class=&quot; , &quot;'&quot; , &quot;no-margin font14 pad05&quot; , &quot;'&quot; , &quot;>Diagnosis code order is changed, click refresh code on final procedure code&lt;/div>&quot;);
                return r;
            });
        } else {
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;IsBillCodeSeqChanged&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;false&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;[data-ec_cntrlid^=&quot;IsBillCodeSeqChanged&quot;]&quot; , &quot;'&quot; , &quot;).hide();
        }

        plansSectionsForAnnualReason();
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });

    //var asHashKeyData;
    //var mainGridName;
    //var hash = {};

    //function ShowDEPopUp(gridName) {
    //    var asHashKey = userID + &quot;~&quot; + vendorID + &quot;~&quot; + currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;formrootid&quot; , &quot;'&quot; , &quot;);
    //    var title = &quot; , &quot;'&quot; , &quot;Final Diagnosis - Data Entry&quot; , &quot;'&quot; , &quot;;
    //    var callbackUrl = &quot; , &quot;'&quot; , &quot;FinalDiagnosisShowPopUp/ComplexElements&quot; , &quot;'&quot; , &quot;;
    //    var popUpSize = &quot; , &quot;'&quot; , &quot;popup-100&quot; , &quot;'&quot; , &quot;;
    //    var patientEncounterId = currentActivatedForm.attr(&quot; , &quot;'&quot; , &quot;patientEncounterId&quot; , &quot;'&quot; , &quot;);
    //    asHashKeyData = asHashKey + $(gridName.mainElement).find(&quot; , &quot;'&quot; , &quot;[name*=&quot;_GF_&quot;]&quot; , &quot;'&quot; , &quot;).closest(&quot; , &quot;'&quot; , &quot;div[data-sysdef]&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;data-askey&quot; , &quot;'&quot; , &quot;);
    //    mainGridName = gridName;
    //    hash = {};
    //    finalDiagonsisGridView_ShowPopUp(title, callbackUrl, popUpSize, gridName, patientEncounterId);
    //    uniqueElmInterName = {};
    //}




    
        
            
                
                        
                
                
                        
                
                
                        
                
                
                        

                
                
            
        
    
    

	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search Existing Final Diagnoses&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

				
			
		
			
				
					
						
					
						
							
								No. 
							
						
							
								Code 
							
						
							
								Description 
							
						
							
								Coding System 
							
						
							
								Actions 
							
						
							
								# 
							
						
					
				
			
		
			
				
					
				
					    
				
					
						No data to display
					
				
			
		
			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DIAG_POINTER_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_DESCRIPTION_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;CODING_SYSTEM_GF_e65e_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:finalDiagnoses_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:finalDiagnoses_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;guYO4+k0S5jwy1tF/QoXin9jKJ7n7ykTCy4dMZxMd9fHKtzpRbLNKZ8aOp40PYwzMa4SFvnfbU2DRs9T2IHy5y8fgk0byRib9oTe8rud6PgWdGkG4L5tYvTPiI/n2sTl1RMn+JwVUVPnzYxKqdV/5JOnBD9ezhljH3+8ppdkXPuHIy4yvvWCmo1ZLV32lN/422XFEjtsCynatWQz2Pju6sz7lHInsaOlkMks/HiGUzMzXA2UhS2pkdIeAHSbCR/dVTSEKCUaDsAzkkb7uHL+Q/3jmDGHNrAZNaNoOSxOA24PyffcnwLLudTnDegaijm7hU+IkGQQzTxvDX4bj3Bjfh5aTDpcejqWSvZuq0IW06zqeu0jf1YOjkoP5tM6Xz0SR9awR3aq6Ty1zmV5OuwiRJ4pqjCi1XiG598ibt3TbZfuKtj11dA00r9eqMumQJg/50B/tqy2/89DDVMxLki7tSt+iILg0j6Gnbv8Vgo4q+eO9dFK+wNmcSjNK5C/Trza48jhtHyW9bFPqox93045PUWJm/k=&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_DIAG_POINTER&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;No.&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DIAGNOSIS_CODE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Code&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DIAGNOSIS_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Description&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_CODING_SYSTEM&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Coding System&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Actions&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_digcode_ptrs&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RecordId&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DIAG_POINTER&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;CODING_SYSTEM&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[5],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;DIAG_POINTER&quot; , &quot;'&quot; , &quot;,0,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE&quot; , &quot;'&quot; , &quot;,0,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;DIAGNOSIS_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;,0,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;CODING_SYSTEM&quot; , &quot;'&quot; , &quot;,0,,,,3],[5,,&quot; , &quot;'&quot; , &quot;No_Focus&quot; , &quot;'&quot; , &quot;,,0,,,,4,,,,,0]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/FinalDiagnosesGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=e65e&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:finalDiagnosesGridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:enc_grid_end_callback,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:finalDiagnosesGridView_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:gridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;FocusedCellChanging&quot; , &quot;'&quot; , &quot;:onFocusedCellChanging,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:finalDiagnosesGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalDiagnosesGridView_e65e_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;6&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->




    $(document).ready(function () {
        var rows = [];
            var rowKeys = Object.keys(rows)
            for (var r = 0; r &lt; rowKeys.length; r++) {
                complexElementOldData[rows[r].RowGuid] = rows[rowKeys[r]];
            }
            const $editableDiv = $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;);
            $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;,$(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;))
            function adjustHeightFromElement() {
                $editableDiv.css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, &quot; , &quot;'&quot; , &quot;auto&quot; , &quot;'&quot; , &quot;);
                if($editableDiv[0].scrollHeight &lt; parseInt($(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;), 10)){
                    $editableDiv.css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;))
                }
                else
                $editableDiv.css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, $editableDiv[0].scrollHeight + &quot; , &quot;'&quot; , &quot;px&quot; , &quot;'&quot; , &quot;); // Set new height based on content
            }

            if (GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;) != null &amp;&amp; GetSystemPreferenceValue(&quot;EXPAND_TEXT_BOX&quot;).PreferenceValue == &quot;Y&quot;) {
                $editableDiv.on(&quot; , &quot;'&quot; , &quot;focus&quot; , &quot;'&quot; , &quot;, function () {
                    $(this).addClass(&quot; , &quot;'&quot; , &quot;expanded&quot; , &quot;'&quot; , &quot;);
                    adjustHeightFromElement();
                });
                $editableDiv.on(&quot; , &quot;'&quot; , &quot;blur&quot; , &quot;'&quot; , &quot;, function () {
                    $(this).removeClass(&quot; , &quot;'&quot; , &quot;expanded&quot; , &quot;'&quot; , &quot;);
                    $(this).css(&quot; , &quot;'&quot; , &quot;height&quot; , &quot;'&quot; , &quot;, $(&quot; , &quot;'&quot; , &quot;#encounterPlansData&quot; , &quot;'&quot; , &quot;).attr(&quot; , &quot;'&quot; , &quot;orignal-height&quot; , &quot;'&quot; , &quot;)); // Reset to original height
                });
                $editableDiv.on(&quot; , &quot;'&quot; , &quot;input&quot; , &quot;'&quot; , &quot;, function () {
                    adjustHeightFromElement();
                });
            }
    });
   

    

Final Procedures#1Add
    $(document).ready(function () {
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });





    
        
            
                
                    Select Code Bundle
EP Routine Exam + Refract + Optos
Glaucoma 99213 + Pachy,  VF, OCT of Optic nerve
NP Routine Exam + OPT + VF
NP Routine Exam + Refract + Optos
NP Routine Exam + Refract + Optos(on 2nd bill)

                
                
                    
                

                
                        
                
                
                            
                
                
                
                        

                
                
            
        
    

    


	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search Existing Final Procedures&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

				
			
		
			
				
					
						
					
						
							
								Bill To 
							
						
							
								Shortcut 
							
						
							
								Code 
							
						
							
								Description 
							
						
							
								Modifiers 
							
						
							
								Units 
							
						
							
								Diag. Ptrs. 
							
						
							
								Actions 
							
						
							
								# 
							
						
					
				
			
		
			
				
					
				
					       
				
					
						No data to display
					
				
			
		
			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;BILL_TO_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;SHORTCUT_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE_DESCRIPTION_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;MODIFIERS_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;DX_POINTERS_GF_de8b_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,null,null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;NRqHpu6AS8T0kAvz3EscewJfHG4WMYCx69BlQqwshDh4+XIrVMx6Kpoc6FEQHE03hEblUQpdKGLpHyz9waPHkYRrcVU/CWd66hraFJl7yAN6T3rFQwqmgqqa4Jq/u4Dejy0lgbNGVOrIvhrGq9GdkcDapqPBziAfXD6e7fiUcpFwudBw9XTWyJCNg4rszYIZ+bTQlK9lPABJoxd3jS1HqWeOUIwXvbrgaHN2NIoe8pogKqGclnrK8Mzzup67EanROePDn0PmGVQsEDMyjm50DwWUgRUyIJxNKOiCKjKKv/ox7CL2AVqUbKl0WFuFX4z/GlQd1Dhc5NTFxZqBJ3Y11MctEK7lss3aGqXmuoyck3oSwWhP/PXUSjuibwiJ+yAWhfvg2nTFqA1J7OtTIJV6S7SpTP6Tz5DfEYMUW7YpQD4x46hM9PqG392LkdUS6XOFlXvwaIfC4uXt0i4BOnPfjn+39xTxWJEMmd/3gV2fDJVYWQy188AuvnRSYa99n3HFWJKy7D1ue+uEuS82uQJYK+Tg2tWGzK8m7s26lY/eXQu26/HJ15Jjsp3o9Qq82w64Fu3AeR1z9a7w4UygDCrhYy6NIqsfXyIaB4FsXVG0AGS+FQhU&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_BILL_TO&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Bill To&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_SHORTCUT&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Shortcut&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_PROCEDURE_CODE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Code&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_PROCEDURE_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Description&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_MODIFIERS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Modifiers&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_UNITS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Units&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DX_POINTERS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Diag. Ptrs.&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Actions&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5,6,7,8],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,5,6,7,8],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[8],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;BILL_TO&quot; , &quot;'&quot; , &quot;,0,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;SHORTCUT&quot; , &quot;'&quot; , &quot;,0,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE&quot; , &quot;'&quot; , &quot;,0,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;PROCEDURE_CODE_DESCRIPTION&quot; , &quot;'&quot; , &quot;,0,,,,3],[5,,,&quot; , &quot;'&quot; , &quot;MODIFIERS&quot; , &quot;'&quot; , &quot;,0,,,,4],[6,,,&quot; , &quot;'&quot; , &quot;UNITS&quot; , &quot;'&quot; , &quot;,0,,,,5],[7,,,&quot; , &quot;'&quot; , &quot;DX_POINTERS&quot; , &quot;'&quot; , &quot;,0,,,,6],[8,,&quot; , &quot;'&quot; , &quot;No_Focus&quot; , &quot;'&quot; , &quot;,,0,,,,7,,,,,0]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/FinalProceduresGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=de8b&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:finalProceduresGridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:enc_grid_end_callback,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:finalProceduresGridView_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:gridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;FocusedCellChanging&quot; , &quot;'&quot; , &quot;:onFocusedCellChanging,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:finalProceduresGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalProceduresGridView_de8b_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;9&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->

    


    .dxgvSearchPanel_Metropolis.Search_Final_Procedures table > tbody > tr > td:first-child > table {
    width: 73% !important;
}
Final Outbound Documents#1Add    
    $(document).ready(function () {
        setMaxlengthAttribute(true);
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });
  


    
        
            
                
                        
                
                
                    
                
                
                    
                
                
                    
                
                
                        
                
                
                        
                
                
                        
                
                
            
        
    
    

	
		
	
		
			
				
					
				
			
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

		
	

	
		
			
				
			
				
					
						Document 
					
				
					
						Recipients 
					
				
					
						Needs Review? 
					
				
					
						Reviewed? 
					
				
					
						Status 
					
				
					
						  
					
				
					
						
					
				
			
		
	

	
		
			
		
			No
		
			Cataract ConsultationOwvkmi L...NoSent
		
			
				No data to display
			
		
	

	

	
		
			
		
	

&lt;!--
var dxo = ASPx.GetControlCollection().Get(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;);
dxo.SetProperties({&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1d3962c1-4563-4d42-b32e-eedec39611de&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1d3962c1-4563-4d42-b32e-eedec39611de&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null},&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;selectAllBtnStateWithoutPage&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selectAllSettings&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;index&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;mode&quot; , &quot;'&quot; , &quot;:1}],&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;,,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;RECIPIENTS&quot; , &quot;'&quot; , &quot;,,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;,,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;IS_REVIEWED&quot; , &quot;'&quot; , &quot;,,,,,3],[5,,,&quot; , &quot;'&quot; , &quot;STATUS&quot; , &quot;'&quot; , &quot;,,,,,4],[6,,,,0,,,,5,,,,,0],[7,0,,&quot; , &quot;'&quot; , &quot;DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;,,,,,,,,,,,,,1]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1});

//-->

&lt;!--
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis EmptyFocusedRow&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->

	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;focusedRow&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;gtQ5mScUQa42i15C4I6ODTbJhK7cGLWw2bDTsA1JZwnnJjHFTsx/t62d5iELNbjcmOAusn/Tx6HnJGT6zVnFS+AiQeMKsKjY4fs/kbs8N/x4aYRBa1yt60qi4301RagldAulQCOdIBe/TMr7y6Cs6oE8hqQ2IhjAfIcAhllDHn9FF4DVtZtJ2hfD0W9tOBRf5EWY9U7O91AQWasJj7YmzNydxialp2lXbzjDr8y1E4qK8YqB//qFicuGshMXVX7pU5N5G2z9B3YwN7R/EUH58T1iy7BC0MDDfO3CTShV4heQmzLB59/B94/lJCHvMxCr87BRCu/GZB8bzpjEjfwW4SqxVKg7T9Lvs0lPumZbmUJUjd6APGqrDdA2g2iNrDaHsG5WoL+S+3kfmdBHVhkywMeCeEpzHGnA2kHEbSAxZYdDPyFDEC8g3B7G7k3bj+7SNkrV+WD2lxSeZ2mU0unFhPUbzMk4Tw8BGWxfPa8qpNzwjHl+rUw6xogMI0/C3n3f/SsPESyJXLjY6mMQOxduu2p/ib8i0JQ2DkKugza4fge5TYgQoGHQwZaAsfqNpRskQfqgOLG45aSsWGAT6YV/LmYAgb8=&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Document&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_RECIPIENTS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Recipients&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Needs Review?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_IS_REVIEWED&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Reviewed?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_STATUS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Status&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RecordId&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;195&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;STATUS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Not Sent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Complete Eye Exam Report Brief&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RECIPIENTS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Owvkmi Lomjftuh(Patient)&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;IS_REVIEWED&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;False&quot; , &quot;'&quot; , &quot;}},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;195&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[6],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6aee8f53-c6f4-4b90-8f17-ad18f9d69847&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null},&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;selectAllBtnStateWithoutPage&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selectAllSettings&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;index&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;mode&quot; , &quot;'&quot; , &quot;:1}],&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;DOCUMENT_NAME&quot; , &quot;'&quot; , &quot;,,,,,0],[2,,,&quot; , &quot;'&quot; , &quot;RECIPIENTS&quot; , &quot;'&quot; , &quot;,,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;NEEDS_REVIEW&quot; , &quot;'&quot; , &quot;,,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;IS_REVIEWED&quot; , &quot;'&quot; , &quot;,,,,,3],[5,,,&quot; , &quot;'&quot; , &quot;STATUS&quot; , &quot;'&quot; , &quot;,,,,,4],[6,,,,0,,,,5,,,,,0],[7,0,,&quot; , &quot;'&quot; , &quot;DOCUMENT_TYPE&quot; , &quot;'&quot; , &quot;,,,,,,,,,,,,,1]],&quot; , &quot;'&quot; , &quot;pendingEvents&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;RaiseFocusedItemChangedOutOfServer&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/FinalOutboundDocumentGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=d762&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:finalOutboundDocumentgridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:enc_grid_end_callback,&quot; , &quot;'&quot; , &quot;SelectionChanged&quot; , &quot;'&quot; , &quot;:onCheckChangeFOD,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:gridViewFOD_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:gridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:finalOutboundDocumentgridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;finalOutboundDocumentGridView_d762_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis EmptyFocusedRow&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->


    


    .EmptyFocusedRow, .EmptyFocusedRow td {
        background: none !important;
    }


    $(document).ready(function () {
        //$(&quot; , &quot;'&quot; , &quot;.fodTitle&quot; , &quot;'&quot; , &quot;).powerTip({ placement: &quot; , &quot;'&quot; , &quot;sw&quot; , &quot;'&quot; , &quot;, mouseOnToPopup: true }).data(&quot; , &quot;'&quot; , &quot;powertip&quot; , &quot;'&quot; , &quot;, function () {
        //    var r = $(&quot;&lt;p class=&quot; , &quot;'&quot; , &quot;no-margin&quot; , &quot;'&quot; , &quot;> &quot; , &quot;'&quot; , &quot;&quot; + $(this).attr(&quot;data-title&quot;) + &quot;&quot; , &quot;'&quot; , &quot;&lt;/p>&quot;);
        //    return r;
        //});
    });

Amendments#1Add
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
        setMaxlengthAttribute(true);
        if (!($(currentActivatedForm).attr(&quot; , &quot;'&quot; , &quot;islocked&quot; , &quot;'&quot; , &quot;) == &quot;True&quot;)) {
            $(&quot;#SpPlusIcon&quot;).css(&quot;display&quot;,&quot;none&quot;);
        }
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });
   



  
    
        
            
                
                        
                
                
                    
                
                
                        
                
                
            
        
    
    

	
		
			
				
					
						
							
						
					
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtClearDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientButtonEdit,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG$DXSE&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Search Existing Amendments&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;forceShowClearButtonAlways&quot; , &quot;'&quot; , &quot;:true},null,null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

				
			
		
			
				
					
						
					
						
							
								Date 
							
						
							
								Notes 
							
						
							
								Status 
							
						
							
								Source 
							
						
							
								Created By 
							
						
					
				
			
		
			
				
					
				
					
						No data to display
					
				
			
		
			
				
					
						
							Expand All
						
							Collapse All
						
							Sort Ascending
						
							Sort Descending
						
							Clear Sorting
						
							Group By This Column
						
							Ungroup
						
							Group Panel
						
							Show Column
						
							Hide Column
						
							Show Customization Dialog
						
							Column Chooser
						
							Clear Filter
						
							Search Panel
						
							Filter Builder...
						
							Filter Row
						
							Filter Row Menu
						
							Footer
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI9_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI10_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI11_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI12_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI13_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI14_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI15_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI16_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI17_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[null],[null],[null],[null],[null],[null],[null],[null],[null],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowCustDialogHover_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[null],[null],[null],[null],[null],[null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddSelectedItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-checked&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI11_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI13_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI15_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI16_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI17_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[null],[null],[null],[null],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxWeb_mSubMenuItemChecked_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxWeb_mSubMenuItemChecked_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxWeb_mSubMenuItemChecked_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI9_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI10_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI11_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI12_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI13_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI14_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI15_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI16_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI17_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMFullExpandDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMFullCollapseDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSortAscendingDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSortDescendingDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMGroupByColumnDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMGroupByColumnDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowGroupPanelDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowCustDialogDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowCustomizationWindowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMClearFilterDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowSearchPanelDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMShowFilterEditorDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null],[null],[null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG$DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullExpand&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullCollapse&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortAscending&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortDescending&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearSorting&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupByColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;UngroupColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowGroupPanel&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;9&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;HideColumn&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;10&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationDialog&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;11&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationWindow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;12&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearFilter&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;13&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowSearchPanel&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;14&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterEditor&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;15&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;16&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRowMenu&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;17&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFooter&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;9&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;10&quot; , &quot;'&quot; , &quot;:[[1],[0],[1]],&quot; , &quot;'&quot; , &quot;11&quot; , &quot;'&quot; , &quot;:[[1],[0],[1]],&quot; , &quot;'&quot; , &quot;12&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;13&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[0,[-1]]],&quot; , &quot;'&quot; , &quot;14&quot; , &quot;'&quot; , &quot;:[[1],[0],[1]],&quot; , &quot;'&quot; , &quot;15&quot; , &quot;'&quot; , &quot;:[[0,[-1]],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;16&quot; , &quot;'&quot; , &quot;:[[1],[0,[-1]],[1]],&quot; , &quot;'&quot; , &quot;17&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0],[1],[2],[3],[4],[5],[6],[7],[8],[9],[10],[11],[12],[13],[14],[15],[16],[17]]},&quot; , &quot;'&quot; , &quot;allowCheckItems&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;checkedState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;itemCheckedGroups&quot; , &quot;'&quot; , &quot;:[[&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;11&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;13&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;15&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;16&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;17&quot; , &quot;'&quot; , &quot;]],&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullExpand&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;FullCollapse&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortAscending&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;SortDescending&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearSorting&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupByColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;UngroupColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowGroupPanel&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;HideColumn&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationDialog&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowCustomizationWindow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ClearFilter&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowSearchPanel&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterEditor&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFilterRowMenu&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ShowFooter&quot; , &quot;'&quot; , &quot;}]});

//-->

			
				
					
						
							Expand
						
							Collapse
						
							Expand Detail
						
							Collapse Detail
						
							New
						
							Edit
						
							Delete
						
							Group Summary
						
							
						
							Refresh
						
					
				
			
				
					
						
							Sum
						
							Min
						
							Max
						
							Count
						
							Average
						
							None
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXME7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXMBC7_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI7i0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i5_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddSelectedItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-checked&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI7i0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i4_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI5_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI6_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI8_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMExpandRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMCollapseRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMExpandDetailRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMCollapseDetailRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMNewRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMEditRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMDeleteRowDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null],[null,null],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMRefreshDisabled_Metropolis&quot; , &quot;'&quot; , &quot;},null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI7i0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i1_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i2_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i3_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i4_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXI7i5_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;],[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummarySumDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryMinDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryMaxDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryCountDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxGridView_gvCMSummaryAverageDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}],[null]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PImg&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG$DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandDetailRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseDetailRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;NewRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;EditRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;DeleteRow&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMenu&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummarySum&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMin&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMax&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i3&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryCount&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryAverage&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;7i5&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryNone&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Refresh&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;5&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i0&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i1&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i2&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i3&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i4&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;7i5&quot; , &quot;'&quot; , &quot;:[[1],[1],[1]],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0],[1],[2],[3],[4],[5],[6],[7],[8]],&quot; , &quot;'&quot; , &quot;7&quot; , &quot;'&quot; , &quot;:[[0],[1],[2],[3],[4],[5]]},&quot; , &quot;'&quot; , &quot;allowCheckItems&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;checkedState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;itemCheckedGroups&quot; , &quot;'&quot; , &quot;:[[&quot; , &quot;'&quot; , &quot;7i0&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i1&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i2&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i3&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;7i4&quot; , &quot;'&quot; , &quot;]],&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;ExpandDetailRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CollapseDetailRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;NewRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;EditRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;DeleteRow&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummarySum&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMin&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMax&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryCount&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryAverage&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryNone&quot; , &quot;'&quot; , &quot;}],&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;GroupSummaryMenu&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;beginGroup&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Refresh&quot; , &quot;'&quot; , &quot;}]});

//-->

	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/QEABH7hZeGGM/Qm5RGbMVYxTwBxn9Hc7T8sAKhydrFw1TSSwHy6kfsIueNOYirKhaTX/hQl8AzjYyTfiblVmfRaDBZBERdjpW3lPA7hfffImcgMXZSvDnaITPRqrq1Wz9qzrQbvjmjGJYzQLj6HSOdm6t/LB6s6qi2rFGVoM55YzGOCPsBhwhelCW9rqfj9VORWD7TVItklXYfDCEbvKWmQsxiEh33bOQB+e3O5O9Y90EABiege243j79eQTx/fMrZNhykjBKjWUcBRxwFHy/jdDyze83YTC6YHdD0HU3M7599bEfzA5N+fr+ag7c0fQgXnAKAVOTQ6I/zA79ePnV8Gf0+YJh8PKOPKulB3lIE5LJ5JH92jICGt3kZPpi5dbw6e2F0iZuuigtM4ITzanL1rqehooLZA44LplSmfZV47kYsEkhmYTUn1ImwU1CIPHnuHa2LwoN5ibiayNtMXWoQRDxKMTamnm2BfL2B+A81ONv1+ybTN7fAyUQr68O2mx9y+LA==&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cp_CREATED_DATE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Date&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_NOTES&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Notes&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_ADDENDUM_STATUS&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Status&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_SOURCE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Source&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_CREATED_BY&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Created By&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,&quot; , &quot;'&quot; , &quot;CREATED_DATE&quot; , &quot;'&quot; , &quot;,,3,,,0],[1,,,&quot; , &quot;'&quot; , &quot;NOTES&quot; , &quot;'&quot; , &quot;,,,,,1],[2,,,&quot; , &quot;'&quot; , &quot;ADDENDUM_STATUS&quot; , &quot;'&quot; , &quot;,,,,,2],[3,,,&quot; , &quot;'&quot; , &quot;SOURCE&quot; , &quot;'&quot; , &quot;,,,,,3],[4,,,&quot; , &quot;'&quot; , &quot;CREATED_BY&quot; , &quot;'&quot; , &quot;,,,,,4]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/AddendumGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=7294&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:addendumGridView_Init,&quot; , &quot;'&quot; , &quot;ContextMenuItemClick&quot; , &quot;'&quot; , &quot;:encGrid_OnContextMenuItemClick});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;addendumGridView_7294_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;5&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->


    


   div[data-elementinternalname=Addendums] .dxgvSearchPanel_Metropolis > table {
        width: 35%;
    }
   div[data-elementinternalname=Addendums] .dxgvSearchPanel_Metropolis.dxgvSearchPanel_Metropolis_Right_new > table {
        width: 40%;
    }
Signatures#1Add
    $(document).ready(function () {
        /*$.Metro.initDropdowns();*/
        var callfromEncType = &quot;pt_encounter&quot;;
        if (callfromEncType == &quot;designer&quot;) {
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;a[title]&quot;).removeAttr(&quot;title&quot;);
            $(this).find(&quot; , &quot;'&quot; , &quot;span[class*=&quot;disabled&quot;]&quot; , &quot;'&quot; , &quot;).parent(&quot;button[title]&quot;).removeAttr(&quot;title&quot;);
        }
    });





    
        
            
                
                        
                
                
                        
                
                
                        
                    
                
                
            
        
    
    


	
		
			
				
					
						
					
						
							
								Type 
							
						
							
								User 
							
						
							
								Active? 
							
						
							
								Signed? 
							
						
							
								Sign 
							
						
							
								  
							
						
							
								
							
						
					
				
			
		
			
				
					
				
					ProviderPatient PortalYesNo
				
					
						No data to display
					
				
					  NoNo
				
			
		
			
				
					
						
							Edit Value List
						
							
						
					
				
			
				
					
						
							User Types
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXME0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXMBC0_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG$DXContextMenu_Columns&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0]],&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0]]},&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;editVLSimple_User_Types&quot; , &quot;'&quot; , &quot;}],&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;gv_editValueList&quot; , &quot;'&quot; , &quot;}]});

//-->

			
				
					
						
							Edit Value List
						
							
						
					
				
			
				
					
						
							User Types
						
					
				
			
		
&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXME_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXME0_&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;DXMBC0_&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-hovered&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;P&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxm-disabled&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;DXI0i0_&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;T&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupMenu,&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG_DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG$DXContextMenu_Rows&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cpItemsCommands&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Custom&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;cpItemsInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]],&quot; , &quot;'&quot; , &quot;0i0&quot; , &quot;'&quot; , &quot;:[[0],[0],[1]]},&quot; , &quot;'&quot; , &quot;cpType&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;renderData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;:[[0]],&quot; , &quot;'&quot; , &quot;0&quot; , &quot;'&quot; , &quot;:[[0]]},&quot; , &quot;'&quot; , &quot;isContextMenu&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function(s,e){ASPx.GVContextMenuItemClick(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,e)}},null,{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;items&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;editVLSimple_User_Types&quot; , &quot;'&quot; , &quot;}],&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;gv_editValueList&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

	
		
			
				
					
						
							
								
									 
								
							

							
						
					
				
&lt;!--
ASPx.createControl(MVCxClientListBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF_DDD_L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF$DDD$L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;CustomCallback&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;isSyncEnabled&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;isComboBoxList&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hasSampleItem&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hoverClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemHover_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;selectedClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemSelected_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;disabledClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;itemsInfo&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Nurse&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Nurse&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PA&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;PA&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Tech&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Tech&quot; , &quot;'&quot; , &quot;]},{&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Other Staff&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;texts&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;Other Staff&quot; , &quot;'&quot; , &quot;]}]},{&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBSelectedIndexChanged(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); },&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBItemMouseUp(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			
		
	

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxpc-closeBtnHover&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;HCB-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupControl,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF$DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;adjustInnerControlsSizeOnShow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;popupAnimationType&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;slide&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;closeAction&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CloseButton&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupHorizontalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;LeftSides&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupVerticalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Below&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Shown&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.DDBPCShown(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }});

//-->

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemovePressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtDropDownDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],]]);
ASPx.createControl(MVCxClientComboBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;autoCompleteAttribute&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;autocomplete&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;off&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;incrementalFilteringMode&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;None&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;lastSuccessValue&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;islastSuccessValueInit&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;GotFocus&quot; , &quot;'&quot; , &quot;:ComboBox_ShowDropDown,&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown,&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:function(s, e) { GetDXControlsByName(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;).PerformCallback();}},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			


	
		Loading…
	



	
		
	

	
		
			
				
					
						
							
								
									 
								
							

							
								

							
						
					
				
&lt;!--
ASPx.createControl(MVCxClientListBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF_DDD_L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF$DDD$L&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:6,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;CustomCallback&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;isSyncEnabled&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;isComboBoxList&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hasSampleItem&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;isCallbackMode&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;callbackPageSize&quot; , &quot;'&quot; , &quot;:100,&quot; , &quot;'&quot; , &quot;isHasFakeRow&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;hoverClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemHover_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;selectedClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeListBoxItemSelected_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;disabledClasses&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;itemsInfo&quot; , &quot;'&quot; , &quot;:[]},{&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBSelectedIndexChanged(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); },&quot; , &quot;'&quot; , &quot;ItemClick&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.CBLBItemMouseUp(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			
		
	

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxpc-closeBtnHover&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;HCB-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.createControl(ASPxClientPopupControl,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF_DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;uniqueID&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF$DDD&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;adjustInnerControlsSizeOnShow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;popupAnimationType&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;slide&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;closeAction&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;CloseButton&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupHorizontalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;LeftSides&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;popupVerticalAlign&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Below&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Shown&quot; , &quot;'&quot; , &quot;:function (s, e) { ASPx.DDBPCShown(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;, e); }});

//-->

&lt;!--
ASPx.AddHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonHover_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveHoverItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddPressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeButtonEditButtonPressed_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemovePressedItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;]]]);
ASPx.AddDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;I&quot; , &quot;'&quot; , &quot;]],[[&quot; , &quot;'&quot; , &quot;dxeDisabled_Metropolis dxeButtonDisabled_Metropolis&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],[&quot; , &quot;'&quot; , &quot;B-1&quot; , &quot;'&quot; , &quot;],,[[{&quot; , &quot;'&quot; , &quot;spriteCssClass&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxEditors_edtDropDownDisabled_Metropolis&quot; , &quot;'&quot; , &quot;}]],[&quot; , &quot;'&quot; , &quot;Img&quot; , &quot;'&quot; , &quot;]]]);
ASPx.RemoveDisabledItems(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,[[[&quot; , &quot;'&quot; , &quot;B-100&quot; , &quot;'&quot; , &quot;],]]);
ASPx.createControl(MVCxClientComboBox,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;autoCompleteAttribute&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;name&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;autocomplete&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;value&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;off&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;incrementalFilteringMode&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;None&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;isCallbackMode&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;lastSuccessValue&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;islastSuccessValueInit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/GetUsersByType&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:function(s, e) {e.customArgs[&quot; , &quot;'&quot; , &quot;dropDownName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_GF_f659_GF&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;cascadeDropDownName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;keyDownEventName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;signature_KeyDown&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;lostFocusEventName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;signature_LostFocus&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;valueListInternaName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;fieldName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;cascadeFieldName&quot; , &quot;'&quot; , &quot;] =&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;;e.customArgs[&quot; , &quot;'&quot; , &quot;cascadeFieldValue&quot; , &quot;'&quot; , &quot;] = GetDXControlsByName(&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE_GF_f659_GF&quot; , &quot;'&quot; , &quot;).GetValue();},&quot; , &quot;'&quot; , &quot;SelectedIndexChanged&quot; , &quot;'&quot; , &quot;:SignatureonComboBoxSelectedIndexChanged},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;IS_ACTIVE_USER_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;readOnly&quot; , &quot;'&quot; , &quot;:true},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

	
		
	

&lt;!--
ASPx.createControl(ASPxClientTextBox,&quot; , &quot;'&quot; , &quot;HAS_SIGNED_GF_f659_GF&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;scStates&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;scPostfix&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;rawValue&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;readOnly&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;nullText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;No&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:function(s,e){$(s.GetInputElement()).attr(&quot; , &quot;'&quot; , &quot;data-nochangeevent&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;True&quot; , &quot;'&quot; , &quot;);},&quot; , &quot;'&quot; , &quot;LostFocus&quot; , &quot;'&quot; , &quot;:signature_LostFocus,&quot; , &quot;'&quot; , &quot;KeyDown&quot; , &quot;'&quot; , &quot;:signature_KeyDown},null,{&quot; , &quot;'&quot; , &quot;decorationStyles&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;F&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;key&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;N&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;className&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxeNullText_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cssText&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;}]});

//-->

			

			

			
				
					
				
			
		
	

&lt;!--
ASPx.createControl(MVCxClientGridView,&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,{&quot; , &quot;'&quot; , &quot;callBack&quot; , &quot;'&quot; , &quot;:function(arg) { ; },&quot; , &quot;'&quot; , &quot;stateObject&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;keys&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;resizingState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;callbackState&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;UBT9G8BJrzDLM5gbBxe1NQfB9MUk9RxWZ1zoxlDhNQCEpNfEGNmJgG9t3XYCiwuxHcIWwdP45in5jj1i4gQJKrECx/p8iuMj8NZ/qosVWBew7k+k3dpoiKDXAsDQO/vcLXH21OVat+/BRxG+l/05p+KSa3LZ6XwHHg/zkxZlg5jfK2RDrkbK9HTh4taSjftpdDjtqPvYpdcmJqxlLQdu+f8G3ArjbV8B7LVvx2+9ZgJCIFknghwSr8XPIVpsxdG/YSzeNGSKpcotCWnMRfZrMoY4PR/s+MfOGS3LIBeeOYN/bTeYVh1i1ktS69YC+GN6C4wB8cZ3ntrk9XhNN+wTpasjhuaMtGRxq+mRpFRypdiokH/CE/hyTYJ0UvXNGHOZW0FCfE6osOJo3Zq6muHN4gQRPkp4V5pZZIF+pRHyZDtYVsEe5MfmM+uteZTN53dp/lvrKIvEgcHpWSzmcYYCdeyY6NPEICp7kF205gsINQ199rYG/w3hdS9JE7r3fCaUu71Y3MFJhpeW/OGx1HUnvLP1Dh3LhK7FAZzSzl+J1toi9VuuE8nZkuZNIZya0X3rwKk5FQ==&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;groupLevelState&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;scrollState&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selection&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;batchEditClientModifiedValues&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;cp_PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Type&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;User&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_IS_ACTIVE_USER&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Active?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_HAS_SIGNED&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Signed?&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_SIGNATURE_DATE_TIME&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Date &amp; Time&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;cp_gridRowData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;RecordId&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;230&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Patient Portal&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;IS_ACTIVE_USER&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;HAS_SIGNED&quot; , &quot;'&quot; , &quot;:false}},&quot; , &quot;'&quot; , &quot;cp_gridData&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;230:12&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;callBacksEnabled&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;pageRowCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;pageRowSize&quot; , &quot;'&quot; , &quot;:10,&quot; , &quot;'&quot; , &quot;pageIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;pageCount&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;selectedWithoutPageRowCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;checkBoxImageProperties&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxChecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUnchecked_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayed_Metropolis&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;8&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxCheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxUncheckedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;dxWeb_edtCheckBoxGrayedDisabled_Metropolis&quot; , &quot;'&quot; , &quot;]},&quot; , &quot;'&quot; , &quot;icbFocusedStyle&quot; , &quot;'&quot; , &quot;:[&quot; , &quot;'&quot; , &quot;dxICBFocused_Metropolis&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;],&quot; , &quot;'&quot; , &quot;visibleStartIndex&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;focusedRowIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowFocusedRow&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectByItemClick&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowSelectSingleRowOnly&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;vertScroll&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;callbackOnFocusedRowChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;callbackOnSelectionChanged&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;editState&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;editItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;allowBatchEditing&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;batchEditClientState&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;binaryImageColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;hiddenEditorColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;validationInfo&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;editColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,6,7],&quot; , &quot;'&quot; , &quot;startEditAction&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxCallbackModeItemsInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;preventUpdateCellTextForDataItemTemplate&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;comboBoxColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;ellipsisColumns&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;templateColumnIndices&quot; , &quot;'&quot; , &quot;:[1,2,3,4,6,7],&quot; , &quot;'&quot; , &quot;isNewRowOnTop&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnsNullDisplayTextInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;No&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;validateOnEndEdit&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;dataItemTemplateColumnIndices&quot; , &quot;'&quot; , &quot;:[3,4,6,7],&quot; , &quot;'&quot; , &quot;colorColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;allowEndEditOnError&quot; , &quot;'&quot; , &quot;:1,&quot; , &quot;'&quot; , &quot;progressBarColumnIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;comboBoxColumnsWithServerEventsIndices&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;updateInfo&quot; , &quot;'&quot; , &quot;:{},&quot; , &quot;'&quot; , &quot;highlightDeletedItems&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;nonEditableColumnValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;checkColumnsDisplayHtml&quot; , &quot;'&quot; , &quot;:{}},&quot; , &quot;'&quot; , &quot;batchEditPageValues&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;e378ce03-8116-4d94-b542-1c3909850f4c&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Provider&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;Patient Portal&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null},&quot; , &quot;'&quot; , &quot;NIV&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;1&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;2&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;3&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;4&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;6&quot; , &quot;'&quot; , &quot;:null}},&quot; , &quot;'&quot; , &quot;searchPanelFilter&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;selectAllBtnStateWithoutPage&quot; , &quot;'&quot; , &quot;:null,&quot; , &quot;'&quot; , &quot;selectAllSettings&quot; , &quot;'&quot; , &quot;:[{&quot; , &quot;'&quot; , &quot;index&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;mode&quot; , &quot;'&quot; , &quot;:1}],&quot; , &quot;'&quot; , &quot;allowFocusedCell&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowDelete&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowEdit&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;allowInsert&quot; , &quot;'&quot; , &quot;:true,&quot; , &quot;'&quot; , &quot;columnProp&quot; , &quot;'&quot; , &quot;:[[0,,,,0,,,,100,,1,,,0],[1,,,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_TYPE&quot; , &quot;'&quot; , &quot;,,,,,0],[2,,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON_NAME&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;PRACTICE_PERSON&quot; , &quot;'&quot; , &quot;,,,,,1],[3,,,&quot; , &quot;'&quot; , &quot;IS_ACTIVE_USER&quot; , &quot;'&quot; , &quot;,,,,,2],[4,,,&quot; , &quot;'&quot; , &quot;HAS_SIGNED&quot; , &quot;'&quot; , &quot;,,,,,3],[5,0,,&quot; , &quot;'&quot; , &quot;SIGNATURE_DATE_TIME&quot; , &quot;'&quot; , &quot;,,,,,,,,,,,,,1],[6,,,,0,,,,4,,,,,0],[7,,&quot; , &quot;'&quot; , &quot;No_Focus&quot; , &quot;'&quot; , &quot;,,0,,,,5,,,,,0]],&quot; , &quot;'&quot; , &quot;editMode&quot; , &quot;'&quot; , &quot;:4,&quot; , &quot;'&quot; , &quot;indentColumnCount&quot; , &quot;'&quot; , &quot;:0,&quot; , &quot;'&quot; , &quot;allowChangeColumnHierarchy&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;allowMultiColumnAutoFilter&quot; , &quot;'&quot; , &quot;:false,&quot; , &quot;'&quot; , &quot;columnResizeMode&quot; , &quot;'&quot; , &quot;:2,&quot; , &quot;'&quot; , &quot;editingItemVisibleIndex&quot; , &quot;'&quot; , &quot;:-1,&quot; , &quot;'&quot; , &quot;callbackUrl&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;/ComplexElements/SignatureGrid?recordGuid=ea63070d&amp;patientEncounterId=1375&amp;callfrom=gridcallback&amp;eGControlUI=f659&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;keyName&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;RowGuid&quot; , &quot;'&quot; , &quot;},{&quot; , &quot;'&quot; , &quot;Init&quot; , &quot;'&quot; , &quot;:signatureGridView_Init,&quot; , &quot;'&quot; , &quot;BeginCallback&quot; , &quot;'&quot; , &quot;:enc_grid_before_callback,&quot; , &quot;'&quot; , &quot;EndCallback&quot; , &quot;'&quot; , &quot;:signature_grid_end_callback,&quot; , &quot;'&quot; , &quot;BatchEditStartEditing&quot; , &quot;'&quot; , &quot;:signatureGridView_BatchEditStartEditing,&quot; , &quot;'&quot; , &quot;BatchEditEndEditing&quot; , &quot;'&quot; , &quot;:signatureGridView_BatchEditEndEditing,&quot; , &quot;'&quot; , &quot;FocusedCellChanging&quot; , &quot;'&quot; , &quot;:onFocusedCellChanging,&quot; , &quot;'&quot; , &quot;ContextMenuItemClick&quot; , &quot;'&quot; , &quot;:encGrid_OnContextMenuItemClick,&quot; , &quot;'&quot; , &quot;BatchEditRowValidating&quot; , &quot;'&quot; , &quot;:signatureGridView_BatchEditRowValidating});
ASPxClientGridBase.PostponeInitialize(&quot; , &quot;'&quot; , &quot;signatureGridView_f659_EEG&quot; , &quot;'&quot; , &quot;,({&quot; , &quot;'&quot; , &quot;commandButtonIDs&quot; , &quot;'&quot; , &quot;:[],&quot; , &quot;'&quot; , &quot;styleInfo&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;ei&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;&lt;tr class=&quot;dxgvEditingErrorRow_Metropolis&quot;>\r\n\t&lt;td class=&quot;dxgv&quot; data-colSpan=&quot;7&quot;>&lt;/td>\r\n&lt;/tr>&quot; , &quot;'&quot; , &quot;,&quot; , &quot;'&quot; , &quot;fc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedCell_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bec&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemc2&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis wm-hide dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;bemergmc2&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvBatchEditModifiedCell_Metropolis dxgvBatchEditCell_Metropolis wm-hide dxgv&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;sel&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvSelectedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedRow_Metropolis&quot; , &quot;'&quot; , &quot;},&quot; , &quot;'&quot; , &quot;fgi&quot; , &quot;'&quot; , &quot;:{&quot; , &quot;'&quot; , &quot;css&quot; , &quot;'&quot; , &quot;:&quot; , &quot;'&quot; , &quot;dxgvFocusedGroupRow_Metropolis&quot; , &quot;'&quot; , &quot;}}}));

//-->


    $(&quot; , &quot;'&quot; , &quot;.tooltip_sign_off_btn_elem&quot; , &quot;'&quot; , &quot;).powerTip({ placement: &quot; , &quot;'&quot; , &quot;s&quot; , &quot;'&quot; , &quot;, mouseOnToPopup: true }).data(&quot; , &quot;'&quot; , &quot;powertip&quot; , &quot;'&quot; , &quot;, function () {
        debugger
        if ($(this).attr(&quot; , &quot;'&quot; , &quot;data-enc-sign-off-date&quot; , &quot;'&quot; , &quot;) != undefined) {
            var r = $(&quot;&lt;div class=&quot; , &quot;'&quot; , &quot;no-margin font14 pad15&quot; , &quot;'&quot; , &quot;>&lt;div class=&quot; , &quot;'&quot; , &quot;float-left align-right&quot; , &quot;'&quot; , &quot;>&lt;b>Signed: &quot; + $(this).attr(&quot; , &quot;'&quot; , &quot;data-enc-sign-off-date&quot; , &quot;'&quot; , &quot;) + &quot;&lt;/b>&lt;/div>&quot;);
            return r;
        }
        else {
            return &quot;&quot;;
        }
        });


    



&quot;))]</value>
      <webElementGuid>561519c9-8beb-4dbb-86b9-b62fd24303f2</webElementGuid>
   </webElementXpaths>
</WebElementEntity>
