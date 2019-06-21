log.info " request path "+ mockRequest.path
log.info " request method "+mockRequest.method
log.info " request content-type "+mockRequest.request.contentType
log.info " request content "+mockRequest.requestContent
def response = new Expando(responseTime:"190615101430630",responseReference:"HYyLxUnVhY4Ae3Lq",cardTrans:[])
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
		def cardCode = "455444"
		def login = parameters.login
		def sessionId = parameters.sessionId
		if(parametersLength > 10 || parametersLength < 10 || !appPlateform || !appVersion || !appType || !institutionId
			|| !requesTime || !customerPreferedLanguage || !phoneNumber || !cardCode || !login || !sessionId){
			createFormatError(response)
		}else {
			if(appVersion == currentVersion && (appPlateform == "ANDROID" || appPlateform == "IOS") ) {
				log.info "approved or completed successfully"
					response.with {
						responseCode = "000"
						 responseDescription = "approved or completed successfully"
						 def transaction = new Expando(amount : "5000",sign : "D",label : "transaction 1",transDate : "12465688788")
						 transaction.currency = new Expando(id:"5656654",label : "dirham", iden : "MAD")
						 cardTrans << transaction
						 transaction = new Expando(amount : "25000",sign : "C",label : "transaction 2",transDate : "11165688788")
						 transaction.currency = new Expando(id:"554546654",label : "cfa", iden : "XOF")
						 cardTrans << transaction
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