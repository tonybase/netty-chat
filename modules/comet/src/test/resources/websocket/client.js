(function (win) {
    var ws;
    var auth = false;
    var Client = function (options) {
        var MAX_CONNECT_TIME = 10;
        var DELAY = 15000;
        this.options = options || {};
        this.createConnect(MAX_CONNECT_TIME, DELAY);
    }

    Client.prototype.send = function () {
        var to = document.getElementById("to").value;
        var content = document.getElementById("content").value;

        ws.send(JSON.stringify({
            'ver': 1,
            'op': 2,
            'seq': 3,
            'body': {
                'to': to,
                'content': content
            }
        }));
    }

    Client.prototype.createConnect = function (max, delay) {
        var self = this;
        if (max === 0) {
            return;
        }
        connect();

        var heartbeatInterval;

        function connect() {
            ws = new WebSocket('ws://localhost:8090/chat');

            ws.onopen = function () {
                getAuth();
            }

            ws.onmessage = function (evt) {
                var data = JSON.parse(evt.data);
                if (data.op == 1) {
                    auth = true;
                    heartbeat();
                    heartbeatInterval = setInterval(heartbeat, 4 * 60 * 1000);
                }
                if (!auth) {
                    setTimeout(getAuth, delay);
                }
                if (auth && data.op == 2) {
                    var notify = self.options.notify;
                    if (notify) {
                        notify(data.body);
                    }
                }
            }

            ws.onclose = function () {
                if (heartbeatInterval) {
                    clearInterval(heartbeatInterval);
                }
                setTimeout(reConnect, delay);
            }

            function heartbeat() {
                ws.send(JSON.stringify({
                    'ver': 1,
                    'op': 2,
                    'seq': 2,
                    'body': {
                        'to': '1',
                        'content': 'hello'
                    }
                }));
            }

            function getAuth() {
                ws.send(JSON.stringify({
                    'ver': 1,
                    'op': 0,
                    'seq': 1,
                    'body': {
                        'user_id': '1',
                        'token': 'test'
                    }
                }));
            }
        }

        function reConnect() {
            self.createConnect(--max, delay * 2);
        }
    }

    win['MyClient'] = Client;
})(window);