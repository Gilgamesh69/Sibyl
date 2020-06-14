package logan.sybilGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import Controllers.Data_Label_Controller;

/**
*
* @author Owner
*/
public class Edit_Column_Panel extends javax.swing.JPanel{
	private String name;
	private char type;
	private char newType;
	Data_Label_Controller ctrl;
   /**
    * Creates new form Edit_Column_Panel
    */
   public Edit_Column_Panel(String name, char type, Data_Label_Controller ctrl) {
	   this.name = name;
	   this.type = type;
	   this.ctrl = ctrl;
       initComponents();
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   private void initComponents() {

       jPanel1 = new javax.swing.JPanel();
       jLabel1 = new javax.swing.JLabel();
       jPanel2 = new javax.swing.JPanel();
       jComboBox1 = new javax.swing.JComboBox<>();

       setBackground(new java.awt.Color(51, 51, 51));
       setLayout(new java.awt.BorderLayout());

       jPanel1.setBackground(new java.awt.Color(0, 0, 0));
       jPanel1.setPreferredSize(new java.awt.Dimension(400, 50));

       jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
       jLabel1.setForeground(new java.awt.Color(102, 102, 102));
       jLabel1.setText(name);
       jPanel1.add(jLabel1);

       add(jPanel1, java.awt.BorderLayout.NORTH);

       jPanel2.setBackground(new java.awt.Color(51, 51, 51));

       jComboBox1.setBackground(new java.awt.Color(51, 51, 51));
       jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Categorical", "Numeric", "Target", "Meta"}));
       if(type == 'C')
    	   jComboBox1.setSelectedIndex(0);
       if(type == 'N')
    	   jComboBox1.setSelectedIndex(1);
       if(type == 'T')
    	   jComboBox1.setSelectedIndex(2);
       
       jComboBox1.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent event) {
    	        JComboBox<String> combo = (JComboBox<String>) event.getSource();
    	        int selected = combo.getSelectedIndex();
    	 
    	        if (selected == 0) {
    	            ctrl.changeCol(name, 'C');
    	        } else if (selected == 1) {
    	        	ctrl.changeCol(name, 'N');
    	        }else if(selected == 2){
    	        	ctrl.changeCol(name, 'T');
    	        }else {
    	        	ctrl.changeCol(name, 'M');
    	        }
    	        //System.out.println(type);
    	    }
       });
       
       javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
       jPanel2.setLayout(jPanel2Layout);
       jPanel2Layout.setHorizontalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap(329, Short.MAX_VALUE))
       );
       jPanel2Layout.setVerticalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createSequentialGroup()
               .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(0, 230, Short.MAX_VALUE))
       );

       add(jPanel2, java.awt.BorderLayout.CENTER);
   }// </editor-fold>                        


   // Variables declaration - do not modify                     
   private javax.swing.JComboBox<String> jComboBox1;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   // End of variables declaration                   

	public char getNewType() {
		return type;
	}
}