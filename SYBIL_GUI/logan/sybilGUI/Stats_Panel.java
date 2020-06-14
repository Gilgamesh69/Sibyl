package logan.sybilGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Stats_Panel extends javax.swing.JPanel {
	private int W;
	private int H;
	private java.awt.Color main_bg_color;
	private java.awt.Color main_side_color;
    /**
     * Creates new form Stats_Panel
     */
    public Stats_Panel(int W,int H,java.awt.Color main_bg_color, java.awt.Color main_side_color) {
 	   this.main_bg_color = main_bg_color;
 	   this.main_side_color = main_side_color;
    	this.W = W-200;
    	this.H = H-200;
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

        side_panel = new javax.swing.JPanel();
        test = new javax.swing.JButton();
        scatter_plt_btn = new javax.swing.JButton();
        regression = new javax.swing.JButton();
        distribution = new javax.swing.JButton();
        area_compare_btn = new javax.swing.JButton();
        bar_chart_btn = new javax.swing.JButton();
        box_plot_btn = new javax.swing.JButton();
        pie_chart_btn = new javax.swing.JButton();
        main_panel = new javax.swing.JPanel();

        setBackground(main_bg_color);

        side_panel.setBackground(main_side_color);
        side_panel.setPreferredSize(new java.awt.Dimension(100, H));

        test.setBackground(main_side_color);
        test.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_Millenium_Eye_50px_1.png",50,50)); // NOI18N
        test.setToolTipText("all seeing eye");
        test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testAction(evt);
            }
        });
        side_panel.add(test);

        scatter_plt_btn.setBackground(main_side_color);
        scatter_plt_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_scatter_plot_50px.png",50,50)); // NOI18N // NOI18N 
        scatter_plt_btn.setToolTipText("scatter");
        side_panel.add(scatter_plt_btn);

        regression.setBackground(main_side_color);
        regression.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_coordinate_system_50px.png",50,50)); // NOI18N
        regression.setToolTipText("regression");
        side_panel.add(regression);

        distribution.setBackground(main_side_color);
        distribution.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_normal_distribution_histogram_50px.png",50,50)); // NOI18N
        side_panel.add(distribution);

        area_compare_btn.setBackground(main_side_color);
        area_compare_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_area_chart_filled_50px.png",50,50)); // NOI18N
        side_panel.add(area_compare_btn);

        bar_chart_btn.setBackground(main_side_color);
        bar_chart_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_bar_chart_50px_1.png",50,50)); // NOI18N
        side_panel.add(bar_chart_btn);

        box_plot_btn.setBackground(main_side_color);
        box_plot_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_candle_sticks_filled_50px.png",50,50)); // NOI18N
        side_panel.add(box_plot_btn);

        pie_chart_btn.setBackground(main_side_color);
        pie_chart_btn.setIcon(GUI_Util.getIcon("SYBIL_GUI/stats_icons/icons8_doughnut_chart_filled_50px.png",50,50)); // NOI18N
        side_panel.add(pie_chart_btn);

        main_panel.setBackground(Color.GREEN);
        main_panel.setPreferredSize(new java.awt.Dimension(W, H));
        
        HYPO_VIEW = new Hypothesis_Panel(main_panel.getWidth(),main_panel.getHeight(),main_bg_color,main_side_color);
        System.out.println(HYPO_VIEW.getHeight());
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(main_panel);

        main_panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(side_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, W, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(side_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(main_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        //add(side_panel);
        //add(main_panel);
    }// </editor-fold>                        
    private void testAction(java.awt.event.ActionEvent evt) {
		main_panel.removeAll();
		main_panel.add(HYPO_VIEW);
		main_panel.repaint();
		main_panel.revalidate();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton area_compare_btn;
    private javax.swing.JButton distribution;
    private javax.swing.JButton bar_chart_btn;
    private javax.swing.JButton box_plot_btn;
    private javax.swing.JButton pie_chart_btn;
    private javax.swing.JPanel main_panel;
    private javax.swing.JButton regression;
    private javax.swing.JButton scatter_plt_btn;
    private javax.swing.JPanel side_panel;
    private javax.swing.JButton test;
    // End of variables declaration 
    private javax.swing.JPanel HYPO_VIEW;
    
}