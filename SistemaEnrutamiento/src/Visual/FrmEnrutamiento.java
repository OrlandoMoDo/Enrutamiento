package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Enrutamiento;
import Logico.Red;
import Logico.Router;
import Logico.SistemaEnrutamiento;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmEnrutamiento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnTablaEnrutamiento;
	private JScrollPane scrlTablaEnrutamiento;
	private JTable tblTablaEnrutamiento;
	private static DefaultTableModel modeloTabla;
	private static Object[] row;//Arreglo de objeto.
	private JButton btnSalir;
	private JButton btnModificar;
	private JButton btnAgregar;
	private JPanel pnRuta;
	private JLabel lblNewLabel_1;
	private JButton btnMejorRuta;
	private JPanel pnBotones;
	private JComboBox cbxRedes;
	private JTextField txtDistanciaAdministrativa;
	private JTextField txtMetrica;
	private JComboBox cbxNextHop;
	private JComboBox cbxEnrutamiento;
	private JButton btnGuardarRuta;
	private JPanel pnMejorRuta;
	private JLabel lblNewLabel_4;
	private JComboBox cbxRedDestino;
	private JButton btnCancelar;
	private JButton btnCalcularMejorRuta;
	private JButton btnCancelarMejorRuta;
	private JLabel lblMejorRuta;
	private int selectRow;
	private JButton btnGuardarModificado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Red red = null;
			Router router = new Router("R1");

			for (int i = 0; i < 7; i++) {
				red = new Red(i, i+1, i+2, i+3, i+4);
				SistemaEnrutamiento.getInstance().ingresarRed(red);
				if(i<4) {
					router.ingresarRed(red);
				}
			}
			router.ingresarInterfaces("E0/0");
			router.ingresarInterfaces("E0/1");
			router.ingresarInterfaces("E0/2");
			router.ingresarInterfaces("E0/3");
			router.ingresarNextHop("10.0.0.1");
			router.ingresarNextHop("10.0.0.2");
			router.ingresarNextHop("10.0.0.3");
			router.ingresarNextHop("10.0.0.4");
			SistemaEnrutamiento.getInstance().ingresarRouter(router);
			
			FrmEnrutamiento dialog = new FrmEnrutamiento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmEnrutamiento() {
		setResizable(false);
		setTitle("Tabla de enrutamiento - R2");
		setModal(true);
		setBounds(100, 100, 660, 670);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			pnTablaEnrutamiento = new JPanel();
			pnTablaEnrutamiento.setBounds(15, 68, 608, 169);
			contentPanel.add(pnTablaEnrutamiento);
			pnTablaEnrutamiento.setLayout(new BorderLayout(0, 0));
			{
				scrlTablaEnrutamiento = new JScrollPane();
				scrlTablaEnrutamiento.setEnabled(false);
				scrlTablaEnrutamiento.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				pnTablaEnrutamiento.add(scrlTablaEnrutamiento, BorderLayout.CENTER);
				
				tblTablaEnrutamiento = new JTable();
				tblTablaEnrutamiento.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
//						JOptionPane
						selectRow = tblTablaEnrutamiento.getSelectedRow();
						if(selectRow != -1 && selectRow>=4) {
							btnModificar.setEnabled(true);
						}else {
							btnModificar.setEnabled(false);
						}
					}
				});
				scrlTablaEnrutamiento.setViewportView(tblTablaEnrutamiento);
			}
		}
		
		modeloTabla = new DefaultTableModel();
		String[] headers = { "Destino", "Mascara", "C - R", "Enru.", "Next Hoop", "Interfaz", "Dist. Admn.", "Metrica"};
		modeloTabla.setColumnIdentifiers(headers);//Estos seran los encabezados de las columnas.
		
		tblTablaEnrutamiento.setModel(modeloTabla);	
		tblTablaEnrutamiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		
		JLabel lblNewLabel = new JLabel("Tabla de enrutamiento - R2");
		lblNewLabel.setBounds(15, 32, 232, 20);
		contentPanel.add(lblNewLabel);
		{
			pnRuta = new JPanel();
			pnRuta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Agregar - Modificar Ruta", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnRuta.setBounds(15, 253, 608, 169);
			contentPanel.add(pnRuta);
			pnRuta.setLayout(null);
			{
				lblNewLabel_1 = new JLabel("Destino:");
				lblNewLabel_1.setBounds(15, 30, 69, 20);
				pnRuta.add(lblNewLabel_1);
			}
			
			String redesArray[] = new String[3];
			int aux = 0;
			for (int i = 0; i < SistemaEnrutamiento.getInstance().getMisRedes().size(); i++) {
				
				if(i!=0 && i!=2 && i!=3 && i!=6) {
					redesArray[aux] = SistemaEnrutamiento.getInstance().getMisRedes().get(i).getDireccionIp()+"/"+SistemaEnrutamiento.getInstance().getMisRedes().get(i).getMascara();
					aux++;
				}
			}
			cbxRedes = new JComboBox(redesArray);
			cbxRedes.setBounds(125, 27, 187, 26);
			pnRuta.add(cbxRedes);
			
			JLabel lblNewLabel_2 = new JLabel("Enrutamiento:");
			lblNewLabel_2.setBounds(15, 78, 101, 20);
			pnRuta.add(lblNewLabel_2);
			
			cbxEnrutamiento = new JComboBox();
			cbxEnrutamiento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int indiceSeleccionado = cbxEnrutamiento.getSelectedIndex();
					switch (indiceSeleccionado) {
						case 0: txtDistanciaAdministrativa.setText("1"); break;
						case 1: txtDistanciaAdministrativa.setText("120"); break;
						case 2: txtDistanciaAdministrativa.setText("90"); break;
						case 3: txtDistanciaAdministrativa.setText("110"); break;
						case 4: txtDistanciaAdministrativa.setText("100"); break;
					}
				}
			});
			cbxEnrutamiento.setModel(new DefaultComboBoxModel(new String[] {"Estatico", "RIP", "EIGRP", "OSPF", "IGRP"}));
			cbxEnrutamiento.setBounds(125, 69, 187, 26);
			pnRuta.add(cbxEnrutamiento);
			
			JLabel lblNewLabel_3 = new JLabel("Dis. Admn.:");
			lblNewLabel_3.setBounds(349, 78, 86, 20);
			pnRuta.add(lblNewLabel_3);
			
			txtDistanciaAdministrativa = new JTextField();
			txtDistanciaAdministrativa.setBounds(437, 75, 156, 26);
			pnRuta.add(txtDistanciaAdministrativa);
			txtDistanciaAdministrativa.setColumns(10);
			
			JLabel lblMetrica = new JLabel("Metrica:");
			lblMetrica.setBounds(349, 27, 86, 20);
			pnRuta.add(lblMetrica);
			
			txtMetrica = new JTextField();
			txtMetrica.setColumns(10);
			txtMetrica.setBounds(437, 24, 156, 26);
			pnRuta.add(txtMetrica);
			
			btnGuardarRuta = new JButton("Guardar");
			btnGuardarRuta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(camposLlenosAgregarMod()) {
						Red redAux = SistemaEnrutamiento.getInstance().buscarRed(cbxRedes.getSelectedItem().toString());
						
						if(redAux!=null) {
							String interfaz = interfazByNH(cbxNextHop.getSelectedItem().toString());
							Enrutamiento enrutamientoAux = new Enrutamiento(redAux, 
							cbxNextHop.getSelectedItem().toString(), Float.parseFloat(txtMetrica.getText()),
							Float.parseFloat(txtDistanciaAdministrativa.getText()), interfaz, 
							cbxEnrutamiento.getSelectedItem().toString());
							SistemaEnrutamiento.getInstance().ingresarEnrutamiento(enrutamientoAux);
							
							limpiarGuardadoModificado();
							SistemaEnrutamiento.getInstance().getMisRouters().get(0).ingresarEnrutamiento(enrutamientoAux);
							loadTable(1);
							JOptionPane.showMessageDialog(null, "Se agrego correctamente!");
						}

					}else {
						JOptionPane.showMessageDialog(null, "Complete todos los campos!");
					}
				}
			});
			btnGuardarRuta.setBounds(349, 111, 115, 35);
			pnRuta.add(btnGuardarRuta);
			
			JLabel lblNextHop = new JLabel("Next Hop:");
			lblNextHop.setBounds(15, 118, 101, 20);
			pnRuta.add(lblNextHop);
			
			String nextHopArray[] = new String[4];
			for (int i = 0; i < 4; i++) {
				nextHopArray[i] = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getNextHops().get(i);
			}
			cbxNextHop = new JComboBox(nextHopArray);
			cbxNextHop.setBounds(125, 115, 187, 26);
			pnRuta.add(cbxNextHop);
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarAgregado(false);
					btnMejorRuta.setEnabled(true);
					tblTablaEnrutamiento.setEnabled(true);
				}
			});
			btnCancelar.setEnabled(false);
			btnCancelar.setBounds(479, 111, 115, 35);
			pnRuta.add(btnCancelar);
			
			btnGuardarModificado = new JButton("Modificar");
			btnGuardarModificado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(camposLlenosAgregarMod()) {
						Red redAux = SistemaEnrutamiento.getInstance().buscarRed(cbxRedes.getSelectedItem().toString());
						
						if(redAux!=null) {
							String interfaz = interfazByNH(cbxNextHop.getSelectedItem().toString());
							Enrutamiento enrutamientoAux = new Enrutamiento(redAux, 
							cbxNextHop.getSelectedItem().toString(), Float.parseFloat(txtMetrica.getText()),
							Float.parseFloat(txtDistanciaAdministrativa.getText()), interfaz, 
							cbxEnrutamiento.getSelectedItem().toString());
							SistemaEnrutamiento.getInstance().modificarRuta(selectRow, enrutamientoAux);
							
							limpiarGuardadoModificado();
							loadTable(1);
							JOptionPane.showMessageDialog(null, "Se modifico correctamente!");

						}
						habilitarAgregado(false);
						limpiarGuardadoModificado();
						btnGuardarModificado.setEnabled(false);
						btnModificar.setEnabled(false);
						

					}else {
						JOptionPane.showMessageDialog(null, "Complete todos los campos!");
					}
				}
			});
			btnGuardarModificado.setBounds(349, 111, 115, 35);
			pnRuta.add(btnGuardarModificado);
		}
		
		pnMejorRuta = new JPanel();
		pnMejorRuta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mejor Ruta", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnMejorRuta.setBounds(15, 438, 608, 116);
		contentPanel.add(pnMejorRuta);
		pnMejorRuta.setLayout(null);
		
		lblNewLabel_4 = new JLabel("Red de destino:");
		lblNewLabel_4.setBounds(15, 28, 117, 20);
		pnMejorRuta.add(lblNewLabel_4);
		
		cbxRedDestino = new JComboBox();
		cbxRedDestino.setBounds(126, 25, 183, 26);
		pnMejorRuta.add(cbxRedDestino);
		
		btnCalcularMejorRuta = new JButton("Calcular");
		btnCalcularMejorRuta.setBounds(352, 24, 115, 29);
		pnMejorRuta.add(btnCalcularMejorRuta);
		
		btnCancelarMejorRuta = new JButton("Cancelar");
		btnCancelarMejorRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCalculoMejorRuta(false);
				btnAgregar.setEnabled(true);
				tblTablaEnrutamiento.setEnabled(true);
			}
		});
		btnCancelarMejorRuta.setBounds(478, 24, 115, 29);
		pnMejorRuta.add(btnCancelarMejorRuta);
		
		lblMejorRuta = new JLabel("...");
		lblMejorRuta.setBounds(15, 80, 69, 20);
		pnMejorRuta.add(lblMejorRuta);
		{
			pnBotones = new JPanel();
			pnBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnBotones, BorderLayout.SOUTH);
			{
				btnMejorRuta = new JButton("Mejor Ruta");
				btnMejorRuta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						habilitarCalculoMejorRuta(true);
						btnAgregar.setEnabled(false);
						tblTablaEnrutamiento.setEnabled(false);
					}
				});
				pnBotones.add(btnMejorRuta);
			}
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						habilitarAgregado(true);
						btnMejorRuta.setEnabled(false);
						tblTablaEnrutamiento.setEnabled(false);
					}
				});
				pnBotones.add(btnAgregar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						habilitarAgregado(true);
						cargarModificado(selectRow);
						btnGuardarModificado.setVisible(true);
						btnGuardarRuta.setVisible(false);
					}
				});
				btnModificar.setEnabled(false);
				pnBotones.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
						if(opcion==0) {
							dispose();
							
						}
					}
				});
				btnSalir.setActionCommand("Cancel");
				pnBotones.add(btnSalir);
			}
		}
		
		habilitarAgregado(false);
		habilitarCalculoMejorRuta(false);
		loadTable(0);
		btnGuardarModificado.setVisible(false);

		
	}
	
	public void habilitarAgregado(boolean aux) {
		cbxRedes.setEnabled(aux);
		cbxNextHop.setEnabled(aux);
		cbxEnrutamiento.setEnabled(aux);
		txtDistanciaAdministrativa.setEnabled(aux);
		txtMetrica.setEnabled(aux);
		btnGuardarRuta.setEnabled(aux);
		btnCancelar.setEnabled(aux);
	}
	
	public void habilitarCalculoMejorRuta(boolean aux) {
		cbxRedDestino.setEnabled(aux);
		btnCalcularMejorRuta.setEnabled(aux);
		btnCancelarMejorRuta.setEnabled(aux);
	}
	
	public void loadTable(int opcion) {
		modeloTabla.setRowCount(0);//Poner todo en la tabla en 0.
		row = new Object[modeloTabla.getColumnCount()];
		
		if(opcion==0) {
			for(int i=0; i<4; i++) {
				row[0] = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getMisRedes().get(i).getDireccionIp();
				row[1] = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getMisRedes().get(i).getMascara();
				row[2] = "Conectada";
				row[3] = "NA";
				row[4] = "NA";
				row[5] = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getInterfaces().get(i);
				row[6] = "0";
				row[7] = "1";				
				modeloTabla.addRow(row);
			}
		}else if(opcion==1) {
			for (int i = 0; i < (4+SistemaEnrutamiento.getInstance().getMisEnrutamientos().size()); i++) {
				if(i<4) {
					row[0] = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getMisRedes().get(i).getDireccionIp();
					row[1] = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getMisRedes().get(i).getMascara();
					row[2] = "Conectada";
					row[3] = "NA";
					row[4] = "NA";
					row[5] = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getInterfaces().get(i);
					row[6] = "0";
					row[7] = "1";				
				}else {
					row[0] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getRedDestino().getDireccionIp();
					row[1] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getRedDestino().getMascara();
					row[2] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getTipoConexion();
					row[3] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getTipoEnrutamiento();
					row[4] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getNextHop();
					row[5] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getInterfazSalida();
					row[6] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getDistanciaAdministrativa();	
					row[7] = SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i-4).getMetrica();
				}
				modeloTabla.addRow(row);

			}
		}

	}
	


	public boolean camposLlenosAgregarMod() {
		boolean aux = false;
		if(!txtMetrica.getText().equalsIgnoreCase("") && !txtDistanciaAdministrativa.getText().equalsIgnoreCase("")) {
			aux = true;
		}
		return aux;
	}
	
	public void limpiarGuardadoModificado() {
		cbxRedes.setSelectedIndex(0);
		cbxNextHop.setSelectedIndex(0);
		cbxEnrutamiento.setSelectedIndex(0);
		txtDistanciaAdministrativa.setText("");
		txtMetrica.setText("");
		
	}


	public String interfazByNH(String NextHop) {
		int pos = -1;
		for (int i = 0; i < 4; i++) {
			if(NextHop.equalsIgnoreCase(SistemaEnrutamiento.getInstance().getMisRouters().get(0).getNextHops().get(i))) {
				pos = i;
			}
		}
		String aux = "";
		if(pos!=-1) {
			aux = SistemaEnrutamiento.getInstance().getMisRouters().get(0).getInterfaces().get(pos);
		}
		return aux;
	}
	


	public void cargarModificado(int selectRow) {
		cbxRedes.setSelectedItem(SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(selectRow-4).getRedDestino().getDireccionIpMask());
		cbxEnrutamiento.setSelectedItem(SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(selectRow-4).getTipoEnrutamiento());
		cbxNextHop.setSelectedItem(SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(selectRow-4).getNextHop());
		txtDistanciaAdministrativa.setText(""+SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(selectRow-4).getDistanciaAdministrativa());
		txtMetrica.setText(""+SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(selectRow-4).getMetrica());
	}
}

