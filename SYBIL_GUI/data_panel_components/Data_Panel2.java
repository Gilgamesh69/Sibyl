package data_panel_components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Controllers.Data_Controller;
import logan.sybilGUI.GUI_Util;
import logan.sybilGUI.Secondary_View;

public class Data_Panel2 extends Secondary_View{
	
	/**
	 * Contructor
	 * @param width
	 * @param height
	 * @param main_bg_color
	 * @param main_side_color
	 */
	public Data_Panel2(int width, int height, Color main_bg_color, Color main_side_color) {
		super(width, height, main_bg_color, main_side_color);
	}

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   protected void initComponents() {

       side_panel_spacer = new javax.swing.JPanel();
       select_file_btn = new javax.swing.JButton();
       load_data_handler_btn = new javax.swing.JButton();
       edit_data_handler_btn = new javax.swing.JButton();
       new_data_handler_btn = new javax.swing.JButton();

       data_label_panel = new Data_Label_Panel2(W-side_panel.getPreferredSize().width,H, main_bg_color, main_bg_color, 200);
       data_handler_panel = new Data_Handler_Panel(W,H,main_bg_color,main_side_color);

       jLabel1 = new javax.swing.JLabel();
       jLabel2 = new javax.swing.JLabel();

       side_panel_spacer.setBackground(main_side_color);
       side_panel_spacer.setPreferredSize(new java.awt.Dimension(100, 120));

       javax.swing.GroupLayout side_panel_spacerLayout = new javax.swing.GroupLayout(side_panel_spacer);
       side_panel_spacer.setLayout(side_panel_spacerLayout);
       side_panel_spacerLayout.setHorizontalGroup(
           side_panel_spacerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 100, Short.MAX_VALUE)
       );
       side_panel_spacerLayout.setVerticalGroup(
           side_panel_spacerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 120, Short.MAX_VALUE)
       );

       side_panel.add(side_panel_spacer);
       
       select_file_btn.setBackground(main_bg_color);
       select_file_btn.setForeground(main_bg_color);
       select_file_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/data_panel_icons/icons8_import_50px_3.png", 50, 50)); 
       select_file_btn.setPreferredSize(new java.awt.Dimension(50, 50));
       
       
       select_file_btn.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
       			center_panel.removeAll();
       			center_panel.add(data_label_panel);
       			center_panel.repaint();
       			center_panel.revalidate();
          	}
       });
       side_panel.add(select_file_btn);

       load_data_handler_btn.setBackground(main_side_color);
       load_data_handler_btn.setForeground(main_side_color);
       load_data_handler_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/data_panel_icons/icons8_data_configuration_50px_2.png", 50, 50)); // NOI18N
       load_data_handler_btn.setPreferredSize(new java.awt.Dimension(50, 50));
       
       side_panel.add(load_data_handler_btn);

       edit_data_handler_btn.setBackground(main_side_color);
       edit_data_handler_btn.setForeground(main_side_color);
       edit_data_handler_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/data_panel_icons/icons8_horizontal_settings_mixer_50px.png", 50, 50)); 
       edit_data_handler_btn.setPreferredSize(new java.awt.Dimension(50, 50));
       
       edit_data_handler_btn.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
      			center_panel.removeAll();
      			center_panel.add(data_handler_panel);
      			center_panel.repaint();
      			center_panel.revalidate();
         	}
      });
       
       side_panel.add(edit_data_handler_btn);

       new_data_handler_btn.setBackground(main_side_color);
       new_data_handler_btn.setForeground(main_side_color);
       new_data_handler_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/data_panel_icons/icons8_data_recovery_50px.png", 50, 50)); 
       new_data_handler_btn.setPreferredSize(new java.awt.Dimension(50, 50));
       
       side_panel.add(new_data_handler_btn);
       
       add(side_panel, java.awt.BorderLayout.WEST);

       jLabel1.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
       jLabel1.setForeground(new java.awt.Color(153, 153, 153));
       jLabel1.setText("Data Handler:");

       jLabel2.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
       jLabel2.setForeground(new java.awt.Color(153, 153, 153));
       jLabel2.setText("Data File:");
       
       center_panel.setBackground(main_bg_color);
       
       add(center_panel, java.awt.BorderLayout.CENTER);
       
       
   }// </editor-fold>                        
   public Data_Controller getDataCtrl() {
	   return data_label_panel.getDataCtrl();
   }

   // Variables declaration - do not modify
   
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   
   private javax.swing.JButton edit_data_handler_btn;
   private javax.swing.JButton load_data_handler_btn;
   private javax.swing.JButton new_data_handler_btn;
   private javax.swing.JButton select_file_btn;
   private javax.swing.JPanel side_panel_spacer;
   
   private Data_Label_Panel2 data_label_panel;
   private JPanel data_handler_panel;
   // End of variables declaration                   
}
