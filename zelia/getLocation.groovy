log.info " request path "+ mockRequest.path
log.info " request method "+mockRequest.method
log.info " request content-type "+mockRequest.request.contentType
log.info " request content "+mockRequest.requestContent
def response = new Expando(responseTime:"190615101430630",responseReference:"HYyLxUnVhY4Ae3Lq",locations : [])
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
		def lat = parameters.lat
		def lon = parameters.lon
		def login = parameters.login
		def sessionId = parameters.sessionId
		if(parametersLength > 11 || parametersLength < 11 || !appPlateform || !appVersion || !appType || !institutionId
			|| !requesTime || !customerPreferedLanguage || !phoneNumber || !lat || !lon || !login || !sessionId){
			createFormatError(response)
		}else {
			if(appVersion == currentVersion && (appPlateform == "ANDROID" || appPlateform == "IOS") ) {
				if(sessionId != "12345") {
				   log.info sendError(response,101,"service not authorized")
				}else {
					log.info "approved or completed successfully."
					response.with {
						responseCode = "000"
						 responseDescription = "approved or completed successfully."
						 def location = new Expando(type: "type",longitude: "longitude",latitude: "latitude",agentIden: "agentIden",
								agentName: "agentName",agentPhone: "agentPhone",brandIden: "brandIden",brandCorpName: "brandCorpName",
							 brandInit: "brandInit",attNum: "attNum",attLabel: "attLabel",posNum: "posNum",posLabel : "posLabel",
							 addr1: "addr1",addr2: "addr2",city: "city",cityLabel:"cityLabel",zipCode:"zipCode",distance:"distance")
						  locations << location
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
		locations : null
	}
}
return mockResponse.responseContent = groovy.json.JsonOutput.toJson(response)