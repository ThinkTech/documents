log.info " request path "+ mockRequest.path
log.info " request method "+mockRequest.method
log.info " request content-type "+mockRequest.request.contentType
log.info " request content "+mockRequest.requestContent
def response = new Expando(responseTime:"190615101430630",responseReference:"HYyLxUnVhY4Ae3Lq")
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
		def customerPreferedLanguage = parameters.customerPreferedLanguage
		def phoneNumber = parameters.phoneNumber
		def operation = parameters.operation
		def login = parameters.login
		def sessionId = parameters.sessionId
		def cardCode = parameters.cardCode
		def motifCode = parameters.motifCode
		def motifIden = parameters.motifIden
		def motifLabel = parameters.motifLabel
		def pin = parameters.pin
		if(parametersLength > 15 || parametersLength < 15 || !appPlateform || !appVersion || !appType || !institutionId
			|| !requesTime || !customerPreferedLanguage || !phoneNumber || !login || !sessionId || !operation || !cardCode
			|| !motifCode || !motifIden || !motifLabel || !pin){
			createFormatError(response)
		}else {
			if(appVersion == currentVersion && (appPlateform == "ANDROID" || appPlateform == "IOS") ) {
				if(login != "hicham" || sessionId != "12345") {
				   log.info sendError(response,101,"service not authorized")
				}else {
					log.info "approved or completed successfully"
					response.with {
						responseCode = "000"
						 responseDescription = "approved or completed successfully"
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
	log.info e.message
	fillErrorResponse(response)
}

def createFormatError(response) {
	sendError(response,"307","format error")
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
		cards : null
	}
}
return mockResponse.responseContent = groovy.json.JsonOutput.toJson(response)