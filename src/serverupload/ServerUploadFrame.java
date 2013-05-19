/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverupload;

import java.net.*;
import java.io.*;
import static java.lang.Thread.interrupted;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Keiven
 */
public class ServerUploadFrame extends javax.swing.JFrame{
    public ServerUploadFrame() {
        initComponents();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RightClickMenu = new javax.swing.JPopupMenu();
        clearLog = new javax.swing.JMenuItem();
        connect = new javax.swing.JButton();
        disconnect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();

        RightClickMenu.setMinimumSize(new java.awt.Dimension(150, 28));
        RightClickMenu.setPreferredSize(new java.awt.Dimension(150, 28));

        clearLog.setText("Clear Log");
        clearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogActionPerformed(evt);
            }
        });
        RightClickMenu.add(clearLog);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Uploader");
        setResizable(false);

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        disconnect.setText("Disconnect");
        disconnect.setEnabled(false);
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });

        jLabel1.setText("Server Disconnect");

        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        TextArea.setEditable(false);
        TextArea.setBackground(new java.awt.Color(0, 0, 0));
        TextArea.setColumns(20);
        TextArea.setForeground(new java.awt.Color(51, 255, 0));
        TextArea.setRows(5);
        TextArea.setToolTipText("");
        TextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TextAreaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(TextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("<html><body><table width='80'><tr><td align=\"center\">Log</td></tr></table></body></html>", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("<html><body><table width='80'><tr><td align=\"center\">Client</td></tr></table></body></html>", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(connect)
                .addGap(71, 71, 71)
                .addComponent(disconnect)
                .addGap(58, 58, 58)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connect)
                    .addComponent(disconnect)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20))
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("");
        jTabbedPane2.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    private static void Log(String message)
    {
        Date time = new Date();
        TextArea.append("[INFO]["+time.toString()+"] "+ message +"\n");
        DefaultCaret caret = (DefaultCaret)TextArea.getCaret(); //supya jTextarea jadi auto scroll
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }
    
    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        Log("binding on port : "+port);
        Log("waiting for client..");
        msock = new MySock();//Update UI dlu, baru panggil socket, supya thread tidak terjadi tabrakan
        connect.setEnabled(false);
        disconnect.setEnabled(true);
        jLabel1.setText("Server Ready");
        connectionStatus = BEGIN_CONNECT;
    }//GEN-LAST:event_connectActionPerformed

    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed
        msock.MySockInterrupt();
        Log("connection close");
        jLabel1.setText("Server Disconnect");
        connect.setEnabled(true);
        disconnect.setEnabled(false);
        connectionStatus = DISCONNECTED;
    }//GEN-LAST:event_disconnectActionPerformed
    
    private void TextAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextAreaMouseReleased
        if(evt.isPopupTrigger()){
            RightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_TextAreaMouseReleased

    private void clearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLogActionPerformed
        TextArea.setText("");
    }//GEN-LAST:event_clearLogActionPerformed
    
    
    public static void main(String args[]) throws IOException, InterruptedException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServerUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServerUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ServerUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerUploadFrame().setVisible(true);
            }
        });
    }
    
    public static class MySock extends Thread{
        MySock()
        {
            start();
        }
        
        public void run()
        {
            int retVal=0;
            try {
                sock = new ServerSocket(port);
                while(connectionStatus != DISCONNECTED)
                {
                    tempServer = sock.accept();
                    retVal = SameIpChecker(tempServer);
                    if(connectionStatus != DISCONNECTED)
                    {
                        if(retVal < 0 )//jika ip client tidak sama, maka akan diberi tempat baru diarray socket server
                        {
                            server[countUser] = tempServer;
                            user.add(server[countUser].getInetAddress().getHostAddress());
                            Log("PC '"+server[countUser].getInetAddress().getHostAddress()+"' is connected");
                            new ClientHandler(countUser);
                            countUser+=1;
                        }
                        else//jika ip client sama, maka akan ditimpa diarray socket yang lama
                        {
                            server[retVal] = tempServer;
                            user.add(server[retVal].getInetAddress().getHostAddress());
                            new ClientHandler(retVal);
                        }
                    }
                }

            } catch (Exception ex) {
                Log("error: "+ex.getMessage());
            }
        }
        
        
        public int SameIpChecker(Socket SockTemp)
        {
            for(int i=0;i<countUser;i++)
            {
                if(SockTemp.getInetAddress().getHostAddress().toString().equals(server[i].getInetAddress().getHostAddress().toString()))
                    return i;
            }
            return -1;
        }
        
        public void MySockInterrupt()
        {
            try{
                sock.close();
                sock = null;
                for(int i=0;i<countUser;i++)
                {
                    server[i].close();
                    server[i] = null;
                }
                countUser = NULL;
                interrupted();
            }catch(Exception e){}
        }
    }
    
    public static class ClientHandler extends Thread{
        private int IndexOfUser;
        private byte temp = -1;
        private DataInputStream DataIn;
        private DataOutputStream DataOut;
        
        
        ClientHandler(int IndexOfUser)
        {
            this.IndexOfUser = IndexOfUser;
            try {
                DataIn = new DataInputStream(server[IndexOfUser].getInputStream());
                DataOut = new DataOutputStream(server[IndexOfUser].getOutputStream());
            } catch (IOException ex) {
                Log("error: "+ex.getMessage());
            }
            start();
        }
        
        public void run()
        {
            while(true)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {}
                
                ////////// terima status koneksi dari client\\\\\\\\
                try {
                    temp = DataIn.readByte(); //tampung status dalam temp
                } catch (IOException ex) {
                    temp=-1;      //jika terjadi error penampung diberi flag -1;
                }
                ////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\
                
                
                if(temp == 0)///jika status koneksi yang diberi adalah 0
                {
                    try
                    {
                        DataOut.writeByte(0); //kirim balik jawaban ke user
                    }
                    catch(Exception e){}
            
                    temp=-1;
                }
                else if(temp == -1) //terjadi error, hapus user
                {
                    try {
                        Log("user '"+server[IndexOfUser].getInetAddress().getHostAddress()+"' is disconnected");
                        server[IndexOfUser].close();
                        server[IndexOfUser] = null;
                        countUser--;
                    } catch (IOException ex) {}
                }
                else if(temp == 1) //jika flag 1 berarti client ingin upload file
                {
                    new UploadHandler(IndexOfUser);
                    break;
                }
            }
        }
        
    }
    
    public static class UploadHandler extends Thread{
        int user;
        private DataInputStream in;
        private DataOutputStream out;
        private static InputStream inputStream;
        private int buffersize=0;
        private byte[] bytes;
        private int count;
        private static FileInputStream fileInStream;
        private static FileOutputStream fileOutStream;
        private static BufferedInputStream bufIn;
        private static BufferedOutputStream bufOut;
        UploadHandler(int user)
        {
            this.user = user;
            start();
        }
        
        public void run()
        {
            try {
                String FileName=null;
                in = new DataInputStream(server[user].getInputStream());
                //out = new DataOutputStream(server[user].getOutputStream());
                FileName = in.readUTF();
                Log("user send "+FileName);
                inputStream = server[user].getInputStream();
                buffersize = server[user].getReceiveBufferSize();
                fileOutStream = new FileOutputStream("D:\\2"+FileName+"");
                bufOut = new BufferedOutputStream(fileOutStream);
                bytes = new byte[buffersize];
                int count;
                while((count = inputStream.read(bytes)) > 0)
                {
                    bufOut.write(bytes, 0, count);
                }
                /* bufferInput dan output ketika ditutup menyebabkan socket juga ditutup
                * untuk itu server akan menunggu koneksi baru lagi dari user
                */
                bufOut.flush();
                bufOut.close();
                Log("file was saved");
            } catch (IOException ex) {
                Logger.getLogger(ServerUploadFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu RightClickMenu;
    private static javax.swing.JTextArea TextArea;
    private javax.swing.JMenuItem clearLog;
    public static javax.swing.JButton connect;
    public static javax.swing.JButton disconnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables

      // Connect Status Constant
    private final static int NULL = 0;
    private final static int DISCONNECTED = 1;
    private final static int DISCONNECTING = 2;
    private final static int BEGIN_CONNECT = 3;
    private final static int CONNECTED = 4;
    
    
    // Connection status info
    private static int port = 7070;
    private static int connectionStatus = DISCONNECTED;
    private static ServerSocket sock;
    private static Socket socket;
    
    
    //count user
    public static List<String> user = new ArrayList<>();
    public static int countUser = NULL;
    private static int MAX_USER = 2;
    
    //class socket
    MySock msock;
    private static Socket[] server = new Socket[MAX_USER]; // untuk jumlah array clien yang akan masuk
    private static Socket tempServer;
    
    
    //FILE
    
}
