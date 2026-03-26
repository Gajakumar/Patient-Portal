import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PR_Info_Details_SalutationID_d3beb480'), 
    '---Select---\nMr.\nMrs.\nMs.\nMiss\nDr.\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_FirstName'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_MiddleInitial'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_LastName'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_Suffix'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_PreferredName'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PR_PronounIds_d3beb480'), 
    '--Select--\nhe/him/his/his/himself\nshe/her/her/hers/herself\nthey/them/their/theirs/themselves\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_Info_Details_DOB_d3beb480'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_SSN'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PR_Info_Details_GenderID_d3beb480'), 
    '---Select---\nMale\nFemale\nUnknown\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PatientInfo_PatientDetail_PreferredLangua'), 
    '---Select---\nEnglish\nSpanish; Castilian\nChinese\nJapanese\nVietnamese\nKorean\nGerman\nFrench\nItalian\nRussian\nHindi\nPolish\nPortuguese\nIndonesian\nDutch; Flemish\nMongolian\nJavanese\nDeclined To Specify\nAbkhazian\nAchinese\nAcoli\nAdangme\nAdyghe; Adygei\nAfar\nAfrihili\nAfrikaans\nAfro-Asiatic languages\nAinu\nAkan\nAkkadian\nAlbanian\nAleut\nAlgonquian languages\nAltaic languages\nAmharic\nAngika\nApache languages\nArabic\nAragonese\nArapaho\nArawak\nArmenian\nAromanian; Arumanian; Macedo-Romanian\nArtificial languages\nAssamese\nAsturian; Bable; Leonese; Asturleonese\nAthapascan languages\nAustralian languages\nAustronesian languages\nAvaric\nAvestan\nAwadhi\nAymara\nAzerbaijani\nBalinese\nBaltic languages\nBaluchi\nBambara\nBamileke languages\nBanda languages\nBantu languages\nBasa\nBashkir\nBasque\nBatak languages\nBeja; Bedawiyet\nBelarusian\nBemba\nBengali\nBerber languages\nBhojpuri\nBihari languages\nBikol\nBini; Edo\nBislama\nBlin; Bilin\nBlissymbols; Blissymbolics; Bliss\nBokmål, Norwegian; Norwegian Bokmål\nBosnian\nBraj\nBreton\nBuginese\nBulgarian\nBuriat\nBurmese\nCaddo\nCatalan; Valencian\nCaucasian languages\nCebuano\nCeltic languages\nCentral American Indian languages\nCentral Khmer\nChagatai\nChamic languages\nChamorro\nChechen\nCherokee\nCheyenne\nChibcha\nChichewa; Chewa; Nyanja\nChinook jargon\nChipewyan; Dene Suline\nChoctaw\nChurch Slavic; Old Slavonic; Church Slavonic; Old Bulgarian; Old Church Slavonic\nChuukese\nChuvash\nClassical Newari; Old Newari; Classical Nepal Bhasa\nClassical Syriac\nCoptic\nCornish\nCorsican\nCree\nCreek\nCreoles and pidgins\nCreoles and pidgins, English based\nCreoles and pidgins, French-based\nCreoles and pidgins, Portuguese-based\nCrimean Tatar; Crimean Turkish\nCroatian\nCushitic languages\nCzech\nDakota\nDanish\nDargwa\nDelaware\nDinka\nDivehi; Dhivehi; Maldivian\nDogri\nDogrib\nDravidian languages\nDuala\nDutch, Middle (ca.1050-1350)\nDyula\nDzongkha\nEastern Frisian\nEfik\nEgyptian (Ancient)\nEkajuk\nElamite\nEnglish, Middle (1100-1500)\nEnglish, Old (ca.450-1100)\nErzya\nEsperanto\nEstonian\nEwe\nEwondo\nFang\nFanti\nFaroese\nFijian\nFilipino; Pilipino\nFinnish\nFinno-Ugrian languages\nFon\nFrench, Middle (ca.1400-1600)\nFrench, Old (842-ca.1400)\nFriulian\nFulah\nGa\nGaelic; Scottish Gaelic\nGalibi Carib\nGalician\nGanda\nGayo\nGbaya\nGeez\nGeorgian\nGerman, Middle High (ca.1050-1500)\nGerman, Old High (ca.750-1050)\nGermanic languages\nGilbertese\nGondi\nGorontalo\nGothic\nGrebo\nGreek, Ancient (to 1453)\nGreek, Modern (1453-)\nGuarani\nGujarati\nGwich\'in\nHaida\nHaitian; Haitian Creole\nHausa\nHawaiian\nHebrew\nHerero\nHiligaynon\nHimachali languages; Western Pahari languages\nHiri Motu\nHittite\nHmong; Mong\nHungarian\nHupa\nIban\nIcelandic\nIdo\nIgbo\nIjo languages\nIloko\nInari Sami\nIndic languages\nIndo-European languages\nIngush\nInterlingua (International Auxiliary Language Association)\nInterlingue; Occidental\nInuktitut\nInupiaq\nIranian languages\nIrish\nIrish, Middle (900-1200)\nIrish, Old (to 900)\nIroquoian languages\nJudeo-Arabic\nJudeo-Persian\nKabardian\nKabyle\nKachin; Jingpho\nKalaallisut; Greenlandic\nKalmyk; Oirat\nKamba\nKannada\nKanuri\nKara-Kalpak\nKarachay-Balkar\nKarelian\nKaren languages\nKashmiri\nKashubian\nKawi\nKazakh\nKhasi\nKhoisan languages\nKhotanese; Sakan\nKikuyu; Gikuyu\nKimbundu\nKinyarwanda\nKirghiz; Kyrgyz\nKlingon; tlhIngan-Hol\nKomi\nKongo\nKonkani\nKosraean\nKpelle\nKru languages\nKuanyama; Kwanyama\nKumyk\nKurdish\nKurukh\nKutenai\nLadino\nLahnda\nLamba\nLand Dayak languages\nLao\nLatin\nLatvian\nLezghian\nLimburgan; Limburger; Limburgish\nLingala\nLithuanian\nLojban\nLow German; Low Saxon; German, Low; Saxon, Low\nLower Sorbian\nLozi\nLuba-Katanga\nLuba-Lulua\nLuiseno\nLule Sami\nLunda\nLuo (Kenya and Tanzania)\nLushai\nLuxembourgish; Letzeburgesch\nMacedonian\nMadurese\nMagahi\nMaithili\nMakasar\nMalagasy\nMalay\nMalayalam\nMaltese\nManchu\nMandar\nMandingo\nManipuri\nManobo languages\nManx\nMaori\nMapudungun; Mapuche\nMarathi\nMari\nMarshallese\nMarwari\nMasai\nMayan languages\nMende\nMi\'kmaq; Micmac\nMinangkabau\nMirandese\nMohawk\nMoksha\nMon-Khmer languages\nMongo\nMossi\nMultiple languages\nMunda languages\nN\'Ko\nNahuatl languages\nNauru\nNavajo; Navaho\nNdebele, North; North Ndebele\nNdebele, South; South Ndebele\nNdonga\nNeapolitan\nNepal Bhasa; Newari\nNepali\nNias\nNiger-Kordofanian languages\nNilo-Saharan languages\nNiuean\nNo linguistic content; Not applicable\nNogai\nNorse, Old\nNorth American Indian languages\nNorthern Frisian\nNorthern Sami\nNorwegian\nNorwegian Nynorsk; Nynorsk, Norwegian\nNubian languages\nNyamwezi\nNyankole\nNyoro\nNzima\nOccitan (post 1500)\nOfficial Aramaic (700-300 BCE); Imperial Aramaic (700-300 BCE)\nOjibwa\nOriya\nOromo\nOsage\nOssetian; Ossetic\nOtomian languages\nPahlavi\nPalauan\nPali\nPampanga; Kapampangan\nPangasinan\nPanjabi; Punjabi\nPapiamento\nPapuan languages\nPedi; Sepedi; Northern Sotho\nPersian\nPersian, Old (ca.600-400 B.C.)\nPhilippine languages\nPhoenician\nPohnpeian\nPrakrit languages\nProvençal, Old (to 1500);Occitan, Old (to 1500)\nPushto; Pashto\nQuechua\nRajasthani\nRapanui\nRarotongan; Cook Islands Maori\nReserved for local use\nRomance languages\nRomanian; Moldavian; Moldovan\nRomansh\nRomany\nRundi\nSalishan languages\nSamaritan Aramaic\nSami languages\nSamoan\nSandawe\nSango\nSanskrit\nSantali\nSardinian\nSasak\nScots\nSelkup\nSemitic languages\nSerbian\nSerer\nShan\nShona\nSichuan Yi; Nuosu\nSicilian\nSidamo\nSign Languages\nSiksika\nSindhi\nSinhala; Sinhalese\nSino-Tibetan languages\nSiouan languages\nSkolt Sami\nSlave (Athapascan)\nSlavic languages\nSlovak\nSlovenian\nSogdian\nSomali\nSonghai languages\nSoninke\nSorbian languages\nSotho, Southern\nSouth American Indian languages\nSouthern Altai\nSouthern Sami\nSranan Tongo\nStandard Moroccan Tamazight\nSukuma\nSumerian\nSundanese\nSusu\nSwahili\nSwati\nSwedish\nSwiss German; Alemannic; Alsatian\nSyriac\nTagalog\nTahitian\nTai languages\nTajik\nTamashek\nTamil\nTatar\nTelugu\nTereno\nTetum\nThai\nTibetan\nTigre\nTigrinya\nTimne\nTiv\nTlingit\nTok Pisin\nTokelau\nTonga (Nyasa)\nTonga (Tonga Islands)\nTsimshian\nTsonga\nTswana\nTumbuka\nTupi languages\nTurkish\nTurkish, Ottoman (1500-1928)\nTurkmen\nTuvalu\nTuvinian\nTwi\nUdmurt\nUgaritic\nUighur; Uyghur\nUkrainian\nUmbundu\nUncoded languages\nUndetermined\nUpper Sorbian\nUrdu\nUzbek\nVai\nVenda\nVolapük\nVotic\nWakashan languages\nWalloon\nWaray\nWasho\nWelsh\nWestern Frisian\nWolaitta; Wolaytta\nWolof\nXhosa\nYakut\nYao\nYapese\nYiddish\nYoruba\nYupik languages\nZande languages\nZapotec\nZaza; Dimili; Dimli; Kirdki; Kirmanjki; Zazaki\nZenaga\nZhuang; Chuang\nZulu\nZuni\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_Weight'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_HeightFeet'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PatientInfo_PatientDetail_HeightInch'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_Info_Address_Main_Line1_d3beb480'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_Info_Address_Main_Line2_d3beb480'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_Info_Address_Main_City_d3beb480'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PR_Info_Address_Main_StateID_d3beb480'), 
    '---Select---\nALL SAINTS\nBENDALS\nBRANNS HAMLET\nBUCKLEYS\nCASSADA GARDENS\nCEDAR GROVE\nCEDAR VALLEY\nCLARK\'S HILL\nCREEKSIDE\nCROSBIES\nDOWN TOWN\nFIVE ISLANDS VILLAGE\nFORT JAMES\nGOLDEN GROVE\nGRAYS FARM\nGREEN BAY\nHATTON HILL\nHODGES BAY\nJENNINGS\nLOWER GAMBLES\nMARBLE HILL\nMCKINNON’S\nPARADISE VIEW\nPIGGOTTS\nPOTTERS VILLAGE\nPRISON FARM\nRAT ISLAND\nSAINT JOHN\'S\nSCOTTS HILL\nSKERRETT’S\nST. JOHNSONS VILLAGE\nSUTHERLANDS\nUPPER GAMBLES\nVILLA\nYEPTON’S\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_Info_Address_Main_ZipCode_d3beb480'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PR_Info_Address_Main_Country_d3beb480'), 
    '---Select---\nATG\nBHS\nCAN\nDMA\nLCA\nTTO\nUSA\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PR_DdlPhoneType_d3beb4800'), 
    '\nMobile\nEmail\nHome\nDay\nFax\nPager\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_PhoneNumber_d3beb4800'), 
    '')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/select_PR_DdlPhoneType_d3beb4801'), 
    '\nMobile\nEmail\nHome\nDay\nFax\nPager\n')

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/input_PR_EMAIL_d3beb4801'), 
    '')

///////////
WebUI.click(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/a_ui-id-14'))



WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_David Smith'), 'David Smith')



WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_03_16_1982'), '03/16/1982')



WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_lblDashboardPhone'), 
    ' (987) 333-3210 ')



WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_lblDashboardEmail'), 
    '\n                                                        gajakumara@first-insight.com\n                                                    ')

WebUI.rightClick(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_1234 Main Street, Zionsville, YT, 46077'))

WebUI.verifyElementText(findTestObject('Scenario Update1703/Max Syncup data/Page_MaximEyes/span_1234 Main Street, Zionsville, YT, 46077'), 
    '\n                                                        1234 Main Street, Zionsville, YT, 46077-\n                                                    ')

