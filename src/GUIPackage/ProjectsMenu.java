package GUIPackage;

import Controllers.ProjectsController;
import Controllers.UsersController;
import GUIPackage.FormControllers.ProjectsMenuController;
import Models.Milestone;
import Models.Project;
import Models.User;
import Utils.GlobalData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javafx.util.Pair;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProjectsMenu extends javax.swing.JFrame {

    
    private int lastSelected;
    private List<User> managers;
    List<Project> projects;
    List<Integer> projectsIds;
    
    /**
     * Creates new form ProjectMenu
     */
    public ProjectsMenu() {
        initComponents();
        FillManagersList();
     
    }

    public ProjectsMenu(ProjectsMenuController controller) {
        initComponents();

        if (!GlobalData.getRoleTitle().equals("Admin")) {
            jButton3.setVisible(false);
            jButton4.setVisible(false);
            jButton5.setVisible(false);
        }
        else {
            jButton3.addActionListener(controller);
            jButton4.addActionListener(controller);
            jButton5.addActionListener(controller);
        }
        jButton1.addActionListener(controller);
        jButton6.addActionListener(controller);
        
        jButton7.addActionListener(controller);
        jButton8.addActionListener(controller);

    }

    public Project GetSelectedProject() {
        lastSelected = jTable1.getSelectedRow();

        if (lastSelected < 0) {
            return null;
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String projectName = model.getValueAt(lastSelected, 1).toString();
        String clientName = model.getValueAt(lastSelected, 2).toString();
        String description = model.getValueAt(lastSelected, 7).toString();

        Double budget = Double.parseDouble(model.getValueAt(lastSelected, 6).toString());

        try {
            Date startDate = format.parse(model.getValueAt(lastSelected, 4).toString());
            Date endDate = format.parse(model.getValueAt(lastSelected, 5).toString());
            Project project = new Project(projectsIds.get(lastSelected), projectName, clientName, startDate, endDate, budget, description); 
            return project;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    public List<Project> ShowPopulation() {

        projects = ProjectsController.GetAll();
        
        FillManagersList();
        projectsIds = new ArrayList<Integer>();

        DefaultTableModel tModel1 = (DefaultTableModel) jTable1.getModel();
        jTable1.setDefaultEditor(Object.class, null);

        while (tModel1.getRowCount() > 0) {
            tModel1.removeRow(0);
        }
        tModel1.setRowCount(0);

        Object rowData[] = new Object[8];

        for (int i = 0; i < projects.size(); ++i) {
            projectsIds.add(projects.get(i).getId());
            
            Pair<Integer, Integer> taskStatus = ProjectsController.GetProjectStatus(projects.get(i).getId());
            
            rowData[0] = taskStatus.getValue().toString() + 
                        " / " +
                        taskStatus.getKey().toString() + 
                        " tasks completed";
            
            rowData[1] = projects.get(i).getTitle();
            rowData[2] = projects.get(i).getClientName();
            for (User manager : managers) {
                if (projects.get(i).getManagerId() == manager.getId()) {
                    rowData[3] = manager.getUsername();
                }
            }
            rowData[4] = projects.get(i).getStartDate();
            rowData[5] = projects.get(i).getEndDate();
            rowData[6] = projects.get(i).getBudget();
            rowData[7] = projects.get(i).getDescription();

            tModel1.addRow(rowData);

        }
        
        return projects;     
    }
    
    public List<Project> ShowPopulationBetweenDates() {

        Date startDate;
        Date endDate;
        try  {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-d");
            startDate = dateFormatter.parse(jFormattedTextField1.getText());
            endDate = dateFormatter.parse(jFormattedTextField2.getText());
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(null, "Start Date is greater than End Date.");
            projects = ProjectsController.GetAll();
        }
        else {
            projects = ProjectsController.GetAllBetweenDates(startDate, endDate);
        }
        
        FillManagersList();
        projectsIds = new ArrayList<Integer>();

        DefaultTableModel tModel1 = (DefaultTableModel) jTable1.getModel();
        jTable1.setDefaultEditor(Object.class, null);

        while (tModel1.getRowCount() > 0) {
            tModel1.removeRow(0);
        }
        tModel1.setRowCount(0);

        Object rowData[] = new Object[8];

        for (int i = 0; i < projects.size(); ++i) {
            projectsIds.add(projects.get(i).getId());
            
            Pair<Integer, Integer> taskStatus = ProjectsController.GetProjectStatus(projects.get(i).getId());
            
            rowData[0] = taskStatus.getValue().toString() + 
                        " / " +
                        taskStatus.getKey().toString() + 
                        " tasks completed";
            
            rowData[1] = projects.get(i).getTitle();
            rowData[2] = projects.get(i).getClientName();
            for (User manager : managers) {
                if (projects.get(i).getManagerId() == manager.getId()) {
                    rowData[3] = manager.getUsername();
                }
            }
            rowData[4] = projects.get(i).getStartDate();
            rowData[5] = projects.get(i).getEndDate();
            rowData[6] = projects.get(i).getBudget();
            rowData[7] = projects.get(i).getDescription();

            tModel1.addRow(rowData);

        }
        
        return projects;     
    }

    public int getLastSelectedId() {
        lastSelected = jTable1.getSelectedRow();
        return lastSelected;   
    }
    
    private void FillManagersList() {
        managers = UsersController.GetManagers();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.lightGray));
        jTable1.setForeground(new java.awt.Color(55, 55, 55));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Status", "Project Name", "Client", "Manager", "Start Date", "End Date", "Budget", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Log Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("New Project");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Edit Project");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete Project");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("View Milestones");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel3.setText("Projects");

        jLabel11.setText("* Date format is yyyy-mm-dd");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        jFormattedTextField1.setDoubleBuffered(true);

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        jFormattedTextField2.setDoubleBuffered(true);

        jLabel1.setText("End Date: ");

        jLabel2.setText("Start Date: ");

        jButton7.setText("Get Projects Between");
        jButton7.setAutoscrolls(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Get All Projects");
        jButton8.setAutoscrolls(true);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addGap(560, 560, 560)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(763, 763, 763)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53))))
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //new AddProjectMenu().setVisible(true);
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(ProjectsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
      
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectsMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
