log.info " request path "+ mockRequest.path
log.info " request method "+mockRequest.method
log.info " request content-type "+mockRequest.request.contentType
log.info " request content "+mockRequest.requestContent
def response = new Expando(responseTime:"190615101430630",responseReference:"HYyLxUnVhY4Ae3Lq",responseLogin:new Expando())
def currentVersion = "1.0"
try {
	def contentType = mockRequest.request.contentType
	if(contentType == "application/x-www-form-urlencoded") {
		def parameters = [:]
		def pairs = mockRequest.requestContent.split("\\&");
		def parametersLength = pairs.length
		0.upto(parametersLength-1,{
			def fields = pairs[it].split("=");
			   def name = URLDecoder.decode(fields[0], "UTF-8");
			   parameters[name] = URLDecoder.decode(fields[1], "UTF-8");
		})
		parameters.each { log.info " request Parameter { $it.key : $it.value } " }
		def appPlateform = parameters.appPlateform
		def appVersion = parameters.appVersion
		def appType = parameters.appType
		def institutionId = parameters.institutionId
		def requesTime = parameters.requesTime
		def serialNumber = parameters.serialNumber
		def login = parameters.login
		def password = parameters.password
		def customerPreferedLanguage = parameters.customerPreferedLanguage
		if(parametersLength > 9 || parametersLength < 9 || !appPlateform || !appVersion || !appType || !institutionId
			|| !requesTime || !serialNumber || !login || !password || !customerPreferedLanguage) {
			createFormatError(response)
		}else {
			if(appVersion == currentVersion && (appPlateform == "ANDROID" || appPlateform == "IOS") ) {
				if(login=="hicham" && password =="passer"){
					log.info "approved or completed successfully"
					response.with {
						responseCode = "000"
						 responseDescription = "approved or completed successfully"
						 responseLogin.with {
							 id = "24801000100500"
							 type =  "1"
						 }
					}
					if(appType == "C") {
					 response.responseLogin.mcc =  null
					}
					else {
					 response.responseLogin.mcc =  "45666"
					}
					response.with {
						responseLogin.with {
							language = "EN"
							secretQuestion = new Expando(key:"QjOPrv7",value:"Quel est votre animal préféré?")
							addAdr1 = "Street zerktouni"
							addAdr2 = "Maarif 2"
							postalCode = null
							city = "Casablanca"
							countryCode = "MA"
							firstName = "Ahmed"
							lastName = "Ali"
							email = "h.zerouali@gmail.com"
							nationality = "Marocaine"
							defaultAccount = "04801000100500840"
							defaultCurrency = "MAD"
							attempCounter = 0
							deviceStatus = "NEW"
							terminalNumber = null
							pinEntryIndicator = true
							phone = "00249520003699"
							login = login
							tokenLifeTime = "445445577878787"
							photoUrl = "http://172.18.1.212:8082/mobile-web-services/users/11245544"
							institutionCurrencies = []
							institutionCurrencies << new Expando(curCode:"XOF",curDen : "SN", curDecimal : "455656989999")
							institutionCurrencies << new Expando(curCode:"MAD",curDen : "MA", curDecimal : "455666666")
							services = ["service1","service2","service3","service4","service5"]
						}
					}
				}
				else {
					if(login!="hicham") {
						log.info sendError(response,"370","incorrect login")
						fillErrorResponse(response)
					}else if(password!="hicham") {
						log.info sendError(response,"371","incorrect password")
						fillErrorResponse(response)
					}
				}
				  
			}
			else {
				log.info sendError(response,"384","version not supported")
				fillErrorResponse(response)
			}
		}
	
	}else {
		createFormatError(response)
	}
}catch(e) {
	log.info sendError(response,"196","system malfunction")
	fillErrorResponse(response)
}

def createFormatError(response) {
	log.info sendError(response,"307","format error")
	fillErrorResponse(response)
}

def sendError(response,code,description) {
	response.with {
		responseCode = code
		responseDescription = description
	}
	fillErrorResponse(response)
	return description
}

def fillErrorResponse(response) {
	response.with {
		responseLogin.with {
			id = null
			 type =  null
			 mcc =  null
			 language = null
			 secretQuestion = new Expando(key:null,value:null)
			 addAdr1 = null
			 addAdr2 = null
			 postalCode = null
			 city = null
			 countryCode = null
			 firstName = null
			 lastName = null
			 email = null
			 nationality = null
			 defaultAccount = null
			 defaultCurrency = null
			 attempCounter = null
			 deviceStatus = null
			 terminalNumber = null
			 pinEntryIndicator = null
			 phone = null
			 login = null
			 tokenLifeTime = null
			 photoUrl = null
			 institutionCurrencies = null
			 services = null
		}
	}
}
return mockResponse.responseContent = groovy.json.JsonOutput.toJson(response)