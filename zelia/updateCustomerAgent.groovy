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
		def customerAgentId = parameters.customerAgentId
		def mail = parameters.mail
		def address1 = parameters.address1
		def city = parameters.city
		def address2 = parameters.address2
		def login = parameters.login
		def sessionId = parameters.sessionId
		def photoUrl = parameters.photoUrl
		def pin = parameters.pin
		if(parametersLength > 16 || parametersLength < 16 || !appPlateform || !appVersion || !appType || !institutionId
			|| !requesTime || !customerPreferedLanguage || !phoneNumber || !customerAgentId || !mail || !address1 || !city
			|| !address2 || !login || !sessionId || !photoUrl || !pin ){
			createFormatError(response)
		}else {
			if(appVersion == currentVersion && (appPlateform == "ANDROID" || appPlateform == "IOS") ) {
				log.info "approved or completed successfully."
				response.with {
					responseCode = "000"
					 responseDescription = "approved or completed successfully."
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
}
return mockResponse.responseContent = groovy.json.JsonOutput.toJson(response)