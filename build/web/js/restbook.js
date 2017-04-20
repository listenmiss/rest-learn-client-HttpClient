//var bookRestUrl = 'http://localhost:8080/rest-jersey-learn/webapi/media/jackson';
var bookRestUrl = 'http://120.27.235.223:8080/rest-jersey-learn/webapi/media/jackson';
function rest(restUrl, httpMethod, param, contenttype, datatype, callback) {
    jQuery('#resultDiv').html("Loading...");
    var request = jQuery.ajax({
        type: httpMethod,
        url: restUrl,
        data: param,
        contentType: contenttype,
        dataType: datatype
    });
    request.done(function (data) {
        try {
            if (data === null || data === undefined) {
                jQuery('#resultDiv').html("No result from server");
            } else {
                callback(data);
            }
        } catch (e) {
            jQuery('#resultDiv').html(e);
        }
    });
    request.fail(function (textStatus, errorThrown) {
        jQuery('#resultDiv').html(errorThrown + " status=" + textStatus.status);
    });
}
/*GET*/
function getAllBook() {
//    rest(bookRestUrl, 'GET', null, null, 'json', renderGetAll);
    jQuery('#resultDiv').html("Loading...");
    var request = jQuery.ajax({
        type: 'GET',
        url: bookRestUrl,
        data: null,
        contentType: null,
        dataType: 'json'
    });
    request.done(function (data) {
        try {
            if (data === null || data === undefined) {
                jQuery('#resultDiv').html("No result from server");
            } else {
//                var books = data.bookList;
 var books = data.book;
                var result = "";
                for (var i = 0; i < books.length; i++) {
                    result += books[i].bookId + "-" + books[i].bookName + "-" + books[i].publisher + "<br/>";
                }
                $('#resultDiv').html(result);
            }
        } catch (e) {
            jQuery('#resultDiv').html(e);
        }
    });
    request.fail(function (textStatus, errorThrown) {
        jQuery('#resultDiv').html(errorThrown + " status=" + textStatus.status);
    });
}
function getBookByQuery() {
//    rest(bookRestUrl, 'GET', null, null, 'json', renderGetAll);
    jQuery('#resultDiv').html("Loading...");
   var url =bookRestUrl+ $("#queryUrl").val();
    var request = jQuery.ajax({
        type: 'GET',
        url: url,
        data: null,
        contentType: null,
        dataType: 'json'
    });
    request.done(function (data) {
        try {
            if (data === null || data === undefined) {
                jQuery('#resultDiv').html("No result from server");
            } else {
               
                var result = "";
              
                    result += data.bookId + "-" + data.bookName + "-" +data.publisher + "<br/>";
         
                $('#resultDiv').html(result);
            }
        } catch (e) {
            jQuery('#resultDiv').html(e);
        }
    });
    request.fail(function (textStatus, errorThrown) {
        jQuery('#resultDiv').html(errorThrown + " status=" + textStatus.status);
    });
}
function getBookByPath() {
//    rest(bookRestUrl, 'GET', null, null, 'json', renderGetAll);
    jQuery('#resultDiv').html("Loading...");
   var url =bookRestUrl+ $("#pathUrl").val();
    var request = jQuery.ajax({
        type: 'GET',
        url: url,
        data: null,
        contentType: null,
        dataType: 'json'
    });
    request.done(function (data) {
        try {
            if (data === null || data === undefined) {
                jQuery('#resultDiv').html("No result from server");
            } else {
               
                var result = "";
              
                    result += data.bookId + "-" + data.bookName + "-" +data.publisher + "<br/>";
         
                $('#resultDiv').html(result);
            }
        } catch (e) {
            jQuery('#resultDiv').html(e);
        }
    });
    request.fail(function (textStatus, errorThrown) {
        jQuery('#resultDiv').html(errorThrown + " status=" + textStatus.status);
    });
}
/*POST*/
function postBook() {
    var contentType = $("input[name='saveRadio']:checked").val();
    var postData;
    var nameValue = $("#bookName").val();
    var publisherValue = $("#publisher").val();
    if (contentType === "application/xml") {
        postData = "<?xml version='1.0' encoding='UTF-8' standalone=yes'?><book id=''><bookName>"+nameValue+"</bookName><book/>";
    } else {
        postData = JSON.stringify({bookName: nameValue, publisher: publisherValue});
    }
    rest(bookRestUrl, 'POST', postData, contentType, 'json', renderPost);
}
;
/*Render DOM*/
function renderGetAll(data) {
    var books = data.bookList.book;
    var result = "";
    for (var i = 0; i < books.length; i++) {
        result += books[i].bookId + "-" + books[i].bookName + "-" + books[i].publisher + "<br/>";
    }
    $('#resultDiv').html(result);
}
function renderPathGet(data) {
    $('#resultDiv').html("path result: " + data.bookId + "-" + data.bookName + "-" + data.publisher);
}
function renderPost(data) {
    $('#resultDiv').html("DONE! id=" + data.bookId);
}