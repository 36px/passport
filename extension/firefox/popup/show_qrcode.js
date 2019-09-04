
function displayURL(url) {
    var output = document.getElementsByClassName("output");
    output.item(0).textContent = url;

    var svg_img = new QRCode({
        content: url,
        padding: 4,
        width: 256,
        height: 256,
        color: "#000000",
        background: "#ffffff",
        ecl: "M"
    }).svg();

    var code = document.getElementsByClassName("qr-code");
    code.item(0).innerHTML = svg_img;
}

if (browser.tabs && browser.tabs.query) {
    function getActiveTab() {
        return browser.tabs.query({ currentWindow: true, active: true });
    }
    getActiveTab().then(function (data) {
        data = data[0]; /* only tab in set */
        var url = data.url;
        displayURL(url);
    });
}
