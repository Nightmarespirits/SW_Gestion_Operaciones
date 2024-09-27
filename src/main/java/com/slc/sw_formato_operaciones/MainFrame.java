package com.slc.sw_formato_operaciones;

import com.formdev.flatlaf.FlatLightLaf;
import com.slc.sw_formato_operaciones.DAO.OperacionDAO;
import com.slc.sw_formato_operaciones.utils.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import java.util.concurrent.ExecutionException;

public class MainFrame extends JFrame {
    private OperacionDAO operacionDao;
    private Utils utils;
    private JProgressBar progressBar;
    private JPanel loadingPanel;
    private JPanel mainPanel;
    private JTabbedPane jTabbedPane1;
    private JPanel tab_Lavado;
    private JPanel tab_Secado;
    private JPanel tab_Doblado;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, helpMenu, jMenu1;
    private JLabel jLabel2;
    private JLabel statusLabel;
    //Panels lavado secado y doblado
    private JPanel lavadoPanel;
    private JPanel secadoPanel;
    private JPanel dobladoPanel;
    public MainFrame() {
        initLoadingScreen();
        setSize(400, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new ComponentLoader().execute();
        
    }

    private void initLoadingScreen() {
        loadingPanel = new JPanel(new BorderLayout(10, 10));
        progressBar = new JProgressBar(0, 100);
        statusLabel = new JLabel("Inicializando componentes...", SwingConstants.CENTER);

        loadingPanel.add(progressBar, BorderLayout.CENTER);
        loadingPanel.add(statusLabel, BorderLayout.SOUTH);
        loadingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(loadingPanel);
    }

    private void initComponents() {
        operacionDao = new OperacionDAO();
        utils = new Utils();
        mainPanel = new JPanel(new BorderLayout());
        initMenuBar();
        initTabs();
        initMainLayout();
    }
    private void initInternaPanels(){
        lavadoPanel = new LavadoPanel();
        secadoPanel = new SecadoPanel();
        dobladoPanel = new DobladoPanel();
    }
    private void initMenuBar() {
        menuBar = new JMenuBar();

        // Opciones Menu
        fileMenu = new JMenu("Opciones");
        fileMenu.setMnemonic('f');
        addMenuItem(fileMenu, "Reload", evt -> reloadAction());

        jMenu1 = new JMenu("ExportarDatos");
        addMenuItem(jMenu1, "Exportar y Limpiar Datos", evt -> exportAndClearAction());
        addMenuItem(jMenu1, "Solo Exportar (Excel)", evt -> exportExcelAction());

        fileMenu.add(jMenu1);
        addMenuItem(fileMenu, "Salir", evt -> exitAction());
        menuBar.add(fileMenu);

        // Configurar Menu
        editMenu = new JMenu("Configurar");
        editMenu.setMnemonic('e');
        addMenuItem(editMenu, "Locales", evt -> configureLocales());
        addMenuItem(editMenu, "Maquinas", evt -> configureMachines());
        addMenuItem(editMenu, "Empleados", evt -> configureEmployees());
        menuBar.add(editMenu);

        // Ayuda Menu
        helpMenu = new JMenu("Ayuda");
        addMenuItem(helpMenu, "Manual Procesos", evt -> showManual());
        addMenuItem(helpMenu, "Acerca De", evt -> showAbout());
        menuBar.add(helpMenu);
    }

    private void initTabs() {
        jTabbedPane1 = new JTabbedPane();

        tab_Lavado = new JPanel();
        tab_Secado = new JPanel();
        tab_Doblado = new JPanel();

        jTabbedPane1.addTab("Proceso de Lavado", tab_Lavado);
        jTabbedPane1.addTab("Proceso de Secado", tab_Secado);
        jTabbedPane1.addTab("Proceso de Doblado", tab_Doblado);

        jTabbedPane1.addChangeListener(evt -> tabStateChanged(evt));
    }

    private void initMainLayout() {
        jLabel2 = new JLabel("Formato de Operaciones", SwingConstants.CENTER);
        jLabel2.setFont(new Font("Segoe UI", 0, 24));

        mainPanel.add(jLabel2, BorderLayout.NORTH);
        mainPanel.add(jTabbedPane1, BorderLayout.CENTER);
    }

    private void addMenuItem(JMenu menu, String title, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }

    private void loadTabContent(int index) {
        JPanel panel = (JPanel) jTabbedPane1.getComponentAt(index);
        if (panel.getComponentCount() == 0) {
            switch (index) {
                case 0 -> repaintPanel(panel, lavadoPanel );
                case 1 -> repaintPanel(panel, secadoPanel);
                case 2 -> repaintPanel(panel, dobladoPanel);
            }
        }
    }

    private void tabStateChanged(ChangeEvent evt) {
        int selectedIndex = jTabbedPane1.getSelectedIndex();
        loadTabContent(selectedIndex);
        switch (selectedIndex) {
            case 1 -> utils.traspasarProcesoLavado_Secado();
            case 2 -> utils.traspasarProcesoSecado_Doblado();
        }
    }

    private void reloadAction() {
        this.dispose();
        SwingUtilities.invokeLater(() -> new MainFrame());
    }

    private void exportAndClearAction() {
        utils.exportarAExcel();
        operacionDao.deleteOperacionesFinalizadas();
    }

    private void exportExcelAction() {
        utils.exportarAExcel();
    }

    private void exitAction() {
        System.exit(0);
    }

    private void configureLocales() {
        new FrmLocal().setVisible(true);
    }

    private void configureMachines() {
        new FrmMaquina().setVisible(true);
    }

    private void configureEmployees() {
        new FrmEmpleados().setVisible(true);
    }

    private void showManual() {
        JOptionPane.showMessageDialog(this, "Función de Manual de Procesos no implementada aún.");
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this, "Sistema de Gestión de Operaciones Internas para Lavanderias\nVersión 1.0\nDesarrollado por nightmarespirits \n https://github.com/Nightmarespirits/");
    }

    private void repaintPanel(JPanel from, JPanel to) {
        to.setSize(from.getWidth(), from.getHeight());
        to.setLocation(0, 0);
        from.removeAll();
        from.add(to, BorderLayout.CENTER);
        from.revalidate();
        from.repaint();
    }

    private class ComponentLoader extends SwingWorker<Void, Integer> {
        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i <= 100; i += 10) {
                Thread.sleep(100);  // Simula tiempo de carga
                publish(i);
                if (i == 30) initComponents();
                if (i == 70) {
                    statusLabel.setText("Cargando Registros...");
                    initInternaPanels();
                }
            }
            return null;
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            int latestProgress = chunks.get(chunks.size() - 1);
            progressBar.setValue(latestProgress);
        }

        @Override
        protected void done() {
            try {
                get();
                setJMenuBar(menuBar);  // Establecemos el menuBar aquí
                getContentPane().remove(loadingPanel);
                getContentPane().add(mainPanel);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setResizable(false);
                revalidate();
                repaint();
                
                //Inicializar Jpanel Lavado
                repaintPanel((JPanel) jTabbedPane1.getComponentAt(0), lavadoPanel );
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(MainFrame.this,
                    "Error al cargar los componentes: " + e.getMessage() + "\n - " + e.getCause() + "\n - " + e.getStackTrace(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Failed to apply Substance Graphite Look and Feel.");
        }

        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}