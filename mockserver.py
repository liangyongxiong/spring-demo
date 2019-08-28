# -*- coding: utf-8 -*-

import sys
import json


def config_httpd(server_address):
    from http.server import BaseHTTPRequestHandler
    from http.server import HTTPServer

    class MockHTTPRequestHandler(BaseHTTPRequestHandler):
        def make_ttx_response(self):
            self.send_response(200)
            self.send_header('Content-type', 'application/json')
            self.end_headers()

            response = {
                'err': 0,
                'msg': 'success',
                'data': None,
                'log': '',
            }
            self.wfile.write(bytes(json.dumps(response), 'utf8'))

        def do_GET(self):
            print(str(self.headers).strip())
            self.make_ttx_response()

        def do_POST(self):
            content_length = int(self.headers.get('Content-Length') or 0)
            post_data = self.rfile.read(content_length)
            print('Body:', post_data.decode('utf-8'))
            print(str(self.headers).strip())
            self.make_ttx_response()

    httpd = HTTPServer(server_address, MockHTTPRequestHandler)
    return httpd


if __name__ == '__main__':
    if sys.version_info.major != 3:
        print('python 3.x required')
        sys.exit(0)

    port = int(sys.argv[1]) if len(sys.argv) == 2 else 8080
    server_address = ('0.0.0.0', port)
    httpd = config_httpd(server_address)
    print('Starting httpd at %s:%s ...' % server_address)

    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        pass

    httpd.server_close()
    print('\nStopping httpd ...')
