$(document).ready(function () {
    function makeAjaxCall(type) {
        $.ajax({
            url: "/bin/recent-articles",
            type: type,
            success: function (data) {
                $("#response1").text(`Response from ${type} request: ${data}`);
            },
            error: function (xhr, status, error) {
                $("#response1").text(`Error: ${xhr.status} - ${error}`);
            }
        });
    }

    $("#getRequest").on("click", function () {
        makeAjaxCall("GET");
    });

    $("#postRequest").on("click", function () {
        makeAjaxCall("POST");
    });

    $("#putRequest").on("click", function () {
        makeAjaxCall("PUT");
    });

    $("#deleteRequest").on("click", function () {
        makeAjaxCall("DELETE");
    });
});