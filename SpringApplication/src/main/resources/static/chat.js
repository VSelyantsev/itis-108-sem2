let stompClient : null = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);

    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#messages").html("")
}

function connect() {
    console.log("Trying to connect");
    let socket = new SockJS("/message-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log("Connected: " + frame)
        stompClient.subscribe('/topic/message', function (message): void {
            showMessage(JSON.parse(message.body).content)
        })
    })
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'content': $("#message").val(), 'name': $("#name").val()}))
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>")
}

$(function () : void {
    $("form").on('submit', )
})
