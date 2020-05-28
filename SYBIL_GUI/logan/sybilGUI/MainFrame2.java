package logan.sybilGUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Owner
 */
public class MainFrame2 extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame2() {
        initComponents();
        JButton[] buttons = {jButton1,jButton2,jButton3,jButton4,jButton5};
        for(final JButton btn : buttons){
            btn.setBackground(new Color(22,22,22));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener(){

                public void mouseClicked(MouseEvent e) {
                }
                public void mousePressed(MouseEvent e) {
                }
                public void mouseReleased(MouseEvent e) {
                }
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(44,44,44));
                }
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(22,22,22));
                }
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        root_panel = new javax.swing.JPanel();
        side_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        center_panel = new javax.swing.JPanel();
        top_panel = new javax.swing.JPanel();
        sybilLogo_panel = new javax.swing.JPanel();
        //jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        root_panel.setLayout(new java.awt.BorderLayout());

        side_panel.setBackground(new java.awt.Color(22, 22, 22));
        side_panel.setPreferredSize(new java.awt.Dimension(100, 700));

        jPanel1.setBackground(new java.awt.Color(22, 22, 22));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        side_panel.add(jPanel1);

        jButton1.setBackground(new java.awt.Color(20, 20, 20));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
		jButton1.setIcon(GUI_Util.getIcon("oct.png"));
        //jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_octahedron_50px_1.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        side_panel.add(jButton1);

        jButton2.setBackground(new java.awt.Color(20, 20, 20));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        
        
        
        jButton2.setIcon(getIcon("oct.png"));
        jButton2.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        side_panel.add(jButton2);

        
        jButton3.setBackground(new java.awt.Color(20, 20, 20));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(getIcon("oct.png"));
        jButton3.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        side_panel.add(jButton3);
        
        jButton4.setBackground(new java.awt.Color(20, 20, 20));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(getIcon("oct.png"));
        jButton4.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        side_panel.add(jButton4);
        
        jButton5.setBackground(new java.awt.Color(20, 20, 20));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(getIcon("oct.png"));
        jButton5.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        side_panel.add(jButton5);

        root_panel.add(side_panel, java.awt.BorderLayout.WEST);

        center_panel.setBackground(new java.awt.Color(34, 40, 44));
        center_panel.setPreferredSize(new java.awt.Dimension(1100, 700));
        center_panel.setLayout(new java.awt.BorderLayout());

        top_panel.setBackground(new java.awt.Color(34, 40, 44));
        top_panel.setPreferredSize(new java.awt.Dimension(1100, 100));
        top_panel.setRequestFocusEnabled(false);

        sybilLogo_panel.setBackground(new java.awt.Color(34, 40, 44));
        sybilLogo_panel.setPreferredSize(new java.awt.Dimension(300, 100));
        

        sybilLogo_panel.add(new logo(), java.awt.BorderLayout.CENTER);
        
        /*
        jButton6.setBackground(new java.awt.Color(34, 40, 44));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(getIcon("sybilLOGO.png")); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(300, 100));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        //jButton6.setRequestFocusEnabled(false);
        //jButton6.setRolloverEnabled(false);
	*/
        /*
        javax.swing.GroupLayout sybilLogo_panelLayout = new javax.swing.GroupLayout(sybilLogo_panel);
        sybilLogo_panel.setLayout(sybilLogo_panelLayout);
        sybilLogo_panelLayout.setHorizontalGroup(
            sybilLogo_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sybilLogo_panelLayout.createSequentialGroup()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        sybilLogo_panelLayout.setVerticalGroup(
            sybilLogo_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sybilLogo_panelLayout.createSequentialGroup()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
*/
        javax.swing.GroupLayout top_panelLayout = new javax.swing.GroupLayout(top_panel);
        top_panel.setLayout(top_panelLayout);
        top_panelLayout.setHorizontalGroup(
            top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_panelLayout.createSequentialGroup()
                .addComponent(sybilLogo_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 800, Short.MAX_VALUE))
        );
        top_panelLayout.setVerticalGroup(
            top_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, top_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sybilLogo_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        center_panel.add(top_panel, java.awt.BorderLayout.NORTH);

        root_panel.add(center_panel, java.awt.BorderLayout.LINE_END);

        getContentPane().add(root_panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        
    private ImageIcon getIcon(String File) {
    	BufferedImage icon = null;
		try {
			//File file = new File("icons8_octahedron_50px_1.png")
			icon = ImageIO.read(new File(File));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Add custom icons to passwords
		JLabel picLabel = new JLabel();
		picLabel.setSize(50, 50);
		Image dimg = icon.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		return imageIcon;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel center_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    //private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel root_panel;
    private javax.swing.JPanel side_panel;
    private javax.swing.JPanel sybilLogo_panel;
    private javax.swing.JPanel top_panel;
    // End of variables declaration                   
}

