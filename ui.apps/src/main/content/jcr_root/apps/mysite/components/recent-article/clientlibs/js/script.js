$(document).ready(function () {
    $("#dataForm").submit(function (e) {
        e.preventDefault(); // Prevent form submission

        // Collect form data
        const formData = {
            param1: $("#param1").val(),
            param2: $("#param2").val()
        };

        // Send POST request
        $.ajax({
            url: "/bin/mysite/recent-article",
            type: "POST",
            data: formData,
            success: function (data) {
                $("#response").text(`Response: ${data}`);
            },
            error: function (xhr, status, error) {
                $("#response").text(`Error: ${xhr.status} - ${error}`);
            }
        });
    });
});