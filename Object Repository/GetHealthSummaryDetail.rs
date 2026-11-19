<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>GetHealthSummaryDetail</name>
   <tag></tag>
   <elementGuidId>7ad131b1-69ef-46ad-bfb1-f46a2dd569a6</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;IsUpdated\&quot;: false,\n  \&quot;elements\&quot;: [],\n  \&quot;healthSummaryId\&quot;: 1626,\n  \&quot;maximeyesPatientNumber\&quot;: \&quot;100987\&quot;,\n  \&quot;patientEncounterId\&quot;: \&quot;413\&quot;,\n  \&quot;patientName\&quot;: null,\n  \&quot;practiceName\&quot;: \&quot;ptportal278\&quot;,\n  \&quot;ptCustomerId\&quot;: \&quot;1489\&quot;,\n  \&quot;switchUserId\&quot;: 0,\n  \&quot;userId\&quot;: 1109\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>accountid</name>
      <type>Main</type>
      <value>ptportal278</value>
      <webElementGuid>aea52eb3-ead2-4289-804f-6d8bf3601406</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.PP_API_AUTH_TOKEN}</value>
      <webElementGuid>008675b0-73d2-4b67-9618-59ff50998d65</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>sessionguid</name>
      <type>Main</type>
      <value>${GlobalVariable.PP_API_SESSION_GUID}</value>
      <webElementGuid>522fea3b-c8b6-44ac-bc91-ba7602c34222</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>1779e466-04cb-4177-ac8e-7aee99bcd788</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>11.0.0</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://ptportalapiqacert.maximeyes.com/api/PatientPortal/GetHealthSummaryDetail</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
