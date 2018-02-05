/*
 * Copyright (c) 2015, Jim Connors
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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public abstract class GenericSocket implements SocketListener {
    protected Socket socketConnection = null;
    private BufferedWriter output = null;
    private InputStream inputStream;
    private Thread socketReaderThread;

    public void connect() throws IOException {
        initSocketConnection();
        if (socketConnection != null && !socketConnection.isClosed()) {
            inputStream = socketConnection.getInputStream();
            output = new BufferedWriter(new OutputStreamWriter(socketConnection.getOutputStream()));
        }
        socketReaderThread = new SocketReaderThread();
        socketReaderThread.setDaemon(true);
        socketReaderThread.start();
    }

    public void close() {
        try {
            if (socketConnection != null && !socketConnection.isClosed()) {
                socketConnection.close();
            }
            onClosedStatus(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void initSocketConnection() throws IOException;

    public void sendMessage(String message) throws IOException {
        output.write(message);
        output.flush();
    }

    class SocketReaderThread extends Thread {

        @Override
        public void run() {
            byte buffer[] = new byte[1024 * 1];
            String receivedData = "";
            int num;
            if (socketConnection != null && socketConnection.isConnected()) {
                onClosedStatus(false);
            }
            try {
                while (true) {
                    while (inputStream.available() > 0) {
                        TimeUnit.MILLISECONDS.sleep(300);
                        num = inputStream.read(buffer);
                        receivedData = new String(buffer, 0, num);
                        onMessage(receivedData);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getClass().getName());
            } finally {
                close();
            }
        }
    }
}
