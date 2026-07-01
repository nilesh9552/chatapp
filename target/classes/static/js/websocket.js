function initChat(username) {
    var socket = new SockJS('/chat');
    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/messages', function (messageOutput) {
            try {
                var payload = JSON.parse(messageOutput.body);
                addMessage(payload.sender, payload.message);
            } catch (e) {
                console.error('Failed to parse message', e);
            }
        });
    }, function (err) {
        console.error('STOMP error', err);
    });

    var sendBtn = document.getElementById('sendBtn');
    var messageInput = document.getElementById('message');

    if (sendBtn) {
        sendBtn.addEventListener('click', function () {
            var text = (messageInput && messageInput.value) ? messageInput.value.trim() : '';
            if (!text) return;

            var chatMessage = {
                sender: username || 'Anonymous',
                message: text
            };

            stompClient.send('/app/send', {}, JSON.stringify(chatMessage));
            if (messageInput) messageInput.value = '';
        });
    }
}

function addMessage(sender, message) {
    var container = document.getElementById('messages');
    if (!container) return;

    var div = document.createElement('div');
    var b = document.createElement('b');
    b.textContent = sender;
    div.appendChild(b);
    div.appendChild(document.createTextNode(': ' + message));
    div.appendChild(document.createElement('br'));
    div.appendChild(document.createElement('br'));

    container.appendChild(div);
    // scroll to bottom
    container.scrollTop = container.scrollHeight;
}

// Auto-init when page script sets `username` variable
if (typeof username !== 'undefined') {
    window.addEventListener('load', function () {
        initChat(username);
    });
}
