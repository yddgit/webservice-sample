/**
 * Invoke web service using jquery.soap
 */

// web service 命名空间
var namespace = 'http://service.project.my.com/user';
// soap命名空间
var soapNamespace = 'http://schemas.xmlsoap.org/soap/envelope/';

/**
 * 初始化全局配置
 */
$(document).ready(function() {
	// $.soap global configuration
	$.soap({
		url: 'userService',
		appendMethodToURL : false,
		namespaceQualifier: 'ns',
		namespaceURL: namespace,
		async : true,
		soap12 : false,
		beforeSend : function(SOAPEnvelope) {
			resetMessageText();
			var xmlout = dom2html($.parseXML(SOAPEnvelope.toString()).firstChild);
			$('#request-message').val($.trim(xmlout));
		},
		success : function(SOAPResponse) {
			responseNormal();
			var xmlout = dom2html(SOAPResponse.toXML().firstChild);
			$('#response-message').val($.trim(xmlout));
		},
		error : function(SOAPResponse) {
			responseFault();
			var xmlout = dom2html(SOAPResponse.toXML().firstChild);
			$('#response-message').val($.trim(xmlout));
		}
	});
});

/**
 * 调用list接口
 */
function sendList() {
	soapCall('list', {});
}

/**
 * 调用add接口
 */
function sendAdd() {
	soapCall('add', function(SOAPObject) {
		var header = new SOAPObject('ns:authorize').addNamespace('ns', namespace)
			.newChild('authorizedUser')
			.addParameter('username', $("#add-header-username").val())
			.addParameter('password', $("#add-header-password").val())
			.addParameter('nickname', $("#add-header-nickname").val())
			.end();
		var body = new SOAPObject('ns:add').addNamespace('ns', namespace)
			.newChild('user')
			.addParameter('username', $("#add-username").val())
			.addParameter('password', $("#add-password").val())
			.addParameter('nickname', $("#add-nickname").val())
			.end();
		var envelope = new SOAPObject('soap:Envelope').addNamespace('soap', soapNamespace);
		envelope.newChild('soap:Header').appendChild(header);
		envelope.newChild('soap:Body').appendChild(body);
		return envelope;
	});
}

/**
 * 调用login接口
 */
function sendLogin() {
	soapCall('login', function(SOAPObject) {
		var body = new SOAPObject('ns:login').addNamespace('ns', namespace)
			.addParameter('username', $("#login-username").val())
			.addParameter('password', $("#login-password").val());
		var envelope = new SOAPObject('soap:Envelope').addNamespace('soap', soapNamespace);
		envelope.newChild('soap:Body').appendChild(body);
		return envelope;
	});
}

/**
 * 调用delete接口
 */
function sendDelete() {
	soapCall('delete', function(SOAPObject) {
		var header = new SOAPObject('ns:authorize').addNamespace('ns', namespace)
			.newChild('authorizedUser')
			.addParameter('username', $("#delete-header-username").val())
			.addParameter('password', $("#delete-header-password").val())
			.addParameter('nickname', $("#delete-header-nickname").val())
			.end();
		var body = new SOAPObject('ns:delete').addNamespace('ns', namespace)
			.addParameter('username', $("#delete-username").val());
		var envelope = new SOAPObject('soap:Envelope').addNamespace('soap', soapNamespace);
		envelope.newChild('soap:Header').appendChild(header);
		envelope.newChild('soap:Body').appendChild(body);
		return envelope;
	});
}

/**
 * 发送SOAP请求
 * @param method 方法
 * @param data 数据
 */
function soapCall(method, data) {
	$.soap({
		method : method,
		data : data,
		elementName : method, // ONLY applies when the data being built from JSON data.
		SOAPAction : '"' + namespace + '/' + method + '"'
	});	
}

/**
 * 重置Request、Response
 */
function resetMessageText() {
	$('#request-message').val("");
	$('#response-message').val("");
	responseNormal();
}

/**
 * 调用正常时response消息显示样式
 */
function responseNormal() {
	$('#response-message').addClass("response-message");
	$('#response-message').removeClass("soap-fault");
}

/**
 * 调用异常时response消息显示样式
 */
function responseFault() {
	$('#response-message').removeClass("response-message");
	$('#response-message').addClass("soap-fault");
}

/**
 * 将DOM结点转换为HTML
 */
function dom2html(xmldom, tabcount) {
	var whitespace = /^\s+$/;
	var tabs = '  ';
	var xmlout = [];
	tabcount = (tabcount) ? tabcount : 0;

	xmlout.push('\n', repeat(tabs, tabcount), '<', xmldom.nodeName);
	for (var i in xmldom.attributes) {
		var attribute = xmldom.attributes[i];
		if (attribute.nodeType === 2) {
			xmlout.push(' ', attribute.name, '="', attribute.value, '"');
		}
	}
	xmlout.push('>');
	++tabcount;
	for (var j = 0; j < xmldom.childNodes.length; j++) {
		var child = xmldom.childNodes[j];
		if (child.nodeType === 1) {
			xmlout.push(dom2html(child, tabcount));
		}
		if (child.nodeType === 3 && !whitespace.test(child.nodeValue)) {
			xmlout.push(child.nodeValue);
		}
	}
 	if (xmldom.childNodes.length === 1 && xmldom.childNodes[0].nodeType === 3) {
		xmlout.push('</', xmldom.nodeName, '>');
	} else {
		xmlout.push('\n', repeat(tabs, --tabcount),'</', xmldom.nodeName, '>');
	}
	return xmlout.join('');
}

/**
 * 将指定字符重复n次
 * @param x 指定字符
 * @param n 重复次数
 * @returns 字符串
 */
function repeat(x, n) {
	var s = '';
	for (;;) {
		if (n & 1) s += x;
		n >>= 1;
		if (n) x += x;
		else break;
	}
	return s;
}
