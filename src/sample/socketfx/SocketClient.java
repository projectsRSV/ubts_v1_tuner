/*
 * Copyright (c) 2013, Jim Connors
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of this project nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package sample.socketfx;

import javafx.application.Platform;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient extends GenericSocket {
    private int port;
    private String host;
    private SocketListener fxListener;

    @Override
    public void onMessage(final String line) {
        Platform.runLater(() -> fxListener.onMessage(line));
    }

    @Override
    public void onClosedStatus(final boolean isClosed) {
        Platform.runLater(() -> fxListener.onClosedStatus(isClosed));
    }

    @Override
    protected void initSocketConnection() throws IOException {
        socketConnection = new Socket();
//        socketConnection.setReuseAddress(true);
        socketConnection.connect(new InetSocketAddress(host, port), 1000);
    }

    public SocketClient(SocketListener fxListener, String host, int port) {
        this.host = host;
        this.fxListener = fxListener;
        this.port = port;
    }
}
