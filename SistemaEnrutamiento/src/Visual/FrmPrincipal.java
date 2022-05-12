package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	ArrayList<String> redes;
	ArrayList<String> mascaraRedes;
	ArrayList<String> gateways;

	
	//Tamaño de la topologia:
	private int topologyWidht = 350;
	private int topologyHeigh = 350;

	private Image imagenTopologia = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Topologia.png")).getImage().getScaledInstance(topologyWidht, topologyHeigh, Image.SCALE_SMOOTH);
	private JTextField txtRedB;
	private JLabel lblRedB;
	private JTextField txtMaskF;
	private JTextField txtMaskG;
	private JTextField txtRedA;
	private JTextField txtMaskA;
	private JTextField txtMaskB;
	private JTextField txtRedC;
	private JTextField txtMaskC;
	private JTextField txtRedD;
	private JTextField txtMaskD;
	private JTextField txtRedG;
	private JTextField txtRedF;
	private JTextField txtRedE;
	private JTextField txtMaskE;
	private JButton btnComprobar;
	private JButton btnNuevo;
	private JButton btnSalir;
	private JLabel lblTopologia_1;
	private JPanel pnTopologia;
	private JLabel lblIpGatewayRedC;
	private JLabel lblIpGatewayRedA;
	private JLabel lblIpGatewayRedD;
	private JLabel lblIpGatewayRedG;
	private JButton btnSiguiente;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		redes = new ArrayList<String>();
		mascaraRedes = new ArrayList<String>();
		gateways = new ArrayList<String>();
		
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Sistema de Redes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension=getToolkit().getScreenSize();
		setBounds(100, 100, dimension.width-400, dimension.height-120);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnBody = new JPanel();
		pnBody.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnBody, BorderLayout.CENTER);
		pnBody.setLayout(null);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redes.clear();
				mascaraRedes.clear();
				gateways.clear();
				if(camposLlenos()) {
					for(int i=0; i<4; i++) {
						int ip;
						switch (i) {
						case 0:
							ip = calcularIpGateway(txtRedA);
							lblIpGatewayRedA.setText("."+ip);
							lblIpGatewayRedA.setVisible(true);
							break;
							
						case 1:
							ip = calcularIpGateway(txtRedC);
							lblIpGatewayRedC.setText("."+ip);
							lblIpGatewayRedC.setVisible(true);
							break;
						
						case 2:
							ip = calcularIpGateway(txtRedD);
							lblIpGatewayRedD.setText("."+ip);
							lblIpGatewayRedD.setVisible(true);
							break;
						
						case 3:
							ip = calcularIpGateway(txtRedG);
							lblIpGatewayRedG.setText("."+ip);
							lblIpGatewayRedG.setVisible(true);
							break;
						}
					}
					
					btnSiguiente.setEnabled(true);
					redes.add(txtRedA.getText());
					redes.add(txtRedB.getText());
					redes.add(txtRedC.getText());
					redes.add(txtRedD.getText());
					redes.add(txtRedE.getText());
					redes.add(txtRedF.getText());
					redes.add(txtRedG.getText());
					mascaraRedes.add(txtMaskA.getText());
					mascaraRedes.add(txtMaskB.getText());
					mascaraRedes.add(txtMaskC.getText());
					mascaraRedes.add(txtMaskD.getText());
					mascaraRedes.add(txtMaskE.getText());
					mascaraRedes.add(txtMaskF.getText());
					mascaraRedes.add(txtMaskG.getText());
					gateways.add(lblIpGatewayRedA.getText());
					gateways.add(lblIpGatewayRedC.getText());
					gateways.add(lblIpGatewayRedD.getText());
					gateways.add(lblIpGatewayRedG.getText());
					
				}else {
					JOptionPane.showMessageDialog(null, "Llene todos los campos");
				}
			}


		});
		btnComprobar.setBounds(233, 491, 122, 39);
		pnBody.add(btnComprobar);
		
		pnTopologia = new JPanel();
		pnTopologia.setBackground(Color.WHITE);
		pnTopologia.setBounds(64, 69, 695, 398);
		pnBody.add(pnTopologia);
		pnTopologia.setLayout(null);
		
		lblIpGatewayRedG = new JLabel(".128");
		lblIpGatewayRedG.setBounds(396, 294, 69, 20);
		pnTopologia.add(lblIpGatewayRedG);
		
		lblIpGatewayRedD = new JLabel(".128");
		lblIpGatewayRedD.setBounds(182, 317, 69, 20);
		pnTopologia.add(lblIpGatewayRedD);
		
		lblIpGatewayRedA = new JLabel(".128");
		lblIpGatewayRedA.setBounds(231, 43, 69, 20);
		pnTopologia.add(lblIpGatewayRedA);
		
		lblIpGatewayRedC = new JLabel(".128");
		lblIpGatewayRedC.setBounds(396, 156, 69, 20);
		pnTopologia.add(lblIpGatewayRedC);
		
		txtRedG = new JTextField();
		txtRedG.setColumns(10);
		txtRedG.setBounds(288, 226, 109, 26);
		pnTopologia.add(txtRedG);
		
		JLabel lblRedG = new JLabel("Red G:");
		lblRedG.setBounds(310, 204, 69, 20);
		pnTopologia.add(lblRedG);
		
		JLabel label_7 = new JLabel("/");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_7.setBounds(396, 229, 14, 20);
		pnTopologia.add(label_7);
		
		txtMaskG = new JTextField();
		txtMaskG.setColumns(10);
		txtMaskG.setBounds(406, 226, 33, 26);
		pnTopologia.add(txtMaskG);
		
		JLabel label_6 = new JLabel("/");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_6.setBounds(574, 272, 14, 20);
		pnTopologia.add(label_6);
		
		JLabel lblRedF = new JLabel("Red F:");
		lblRedF.setBounds(495, 250, 69, 20);
		pnTopologia.add(lblRedF);
		
		txtRedF = new JTextField();
		txtRedF.setColumns(10);
		txtRedF.setBounds(465, 270, 109, 26);
		pnTopologia.add(txtRedF);
		
		txtMaskF = new JTextField();
		txtMaskF.setColumns(10);
		txtMaskF.setBounds(584, 270, 33, 26);
		pnTopologia.add(txtMaskF);
		
		txtMaskE = new JTextField();
		txtMaskE.setColumns(10);
		txtMaskE.setBounds(363, 370, 33, 26);
		pnTopologia.add(txtMaskE);
		
		JLabel lblRedE = new JLabel("Red E:");
		lblRedE.setBounds(274, 350, 69, 20);
		pnTopologia.add(lblRedE);
		
		JLabel label_5 = new JLabel("/");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(353, 372, 14, 20);
		pnTopologia.add(label_5);
		
		txtRedE = new JTextField();
		txtRedE.setColumns(10);
		txtRedE.setBounds(244, 370, 109, 26);
		pnTopologia.add(txtRedE);
		
		txtMaskD = new JTextField();
		txtMaskD.setColumns(10);
		txtMaskD.setBounds(121, 272, 33, 26);
		pnTopologia.add(txtMaskD);
		
		JLabel label_2 = new JLabel("/");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(111, 275, 14, 20);
		pnTopologia.add(label_2);
		
		JLabel lblRedD = new JLabel("Red D:");
		lblRedD.setBounds(32, 250, 69, 20);
		pnTopologia.add(lblRedD);
		
		txtRedD = new JTextField();
		txtRedD.setColumns(10);
		txtRedD.setBounds(2, 272, 109, 26);
		pnTopologia.add(txtRedD);
		
		JLabel label_3 = new JLabel("/");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(355, 154, 14, 20);
		pnTopologia.add(label_3);
		
		JLabel lblRedC = new JLabel("Red C:");
		lblRedC.setBounds(276, 129, 69, 20);
		pnTopologia.add(lblRedC);
		
		txtMaskC = new JTextField();
		txtMaskC.setColumns(10);
		txtMaskC.setBounds(365, 151, 33, 26);
		pnTopologia.add(txtMaskC);
		
		txtRedC = new JTextField();
		txtRedC.setColumns(10);
		txtRedC.setBounds(244, 151, 109, 26);
		pnTopologia.add(txtRedC);
		
		txtMaskB = new JTextField();
		txtMaskB.setColumns(10);
		txtMaskB.setBounds(516, 77, 33, 26);
		pnTopologia.add(txtMaskB);
		
		txtRedB = new JTextField();
		txtRedB.setColumns(10);
		txtRedB.setBounds(395, 77, 109, 26);
		pnTopologia.add(txtRedB);
		
		JLabel label_1 = new JLabel("/");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(506, 80, 14, 20);
		pnTopologia.add(label_1);
		
		lblRedB = new JLabel("Red B:");
		lblRedB.setBounds(427, 55, 69, 20);
		pnTopologia.add(lblRedB);
		
		JLabel label = new JLabel("/");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(182, 80, 14, 20);
		pnTopologia.add(label);
		
		txtMaskA = new JTextField();
		txtMaskA.setColumns(10);
		txtMaskA.setBounds(192, 77, 33, 26);
		pnTopologia.add(txtMaskA);
		
		JLabel lblRedA = new JLabel("Red A:");
		lblRedA.setBounds(103, 55, 69, 20);
		pnTopologia.add(lblRedA);
		
		txtRedA = new JTextField();
		txtRedA.setBounds(71, 77, 109, 26);
		pnTopologia.add(txtRedA);
		txtRedA.setColumns(10);
		
		JLabel lblTopologia = new JLabel("");
		lblTopologia.setBounds(136, 32, topologyWidht, topologyHeigh);
		lblTopologia.setIcon(new ImageIcon(imagenTopologia));
		//lblTopologia.setBackground((Color.BLACK));
		pnTopologia.add(lblTopologia);
		
		lblTopologia_1 = new JLabel("Topologia:");
		lblTopologia_1.setBounds(280, 10, 233, 20);
		pnTopologia.add(lblTopologia_1);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion = 0;
				if(!txtRedA.getText().equalsIgnoreCase("")) {
					opcion = JOptionPane.showConfirmDialog(null, "Esta seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
				}
				if(opcion==0) {
					inicio();
					btnComprobar.setEnabled(true);
					txtRedA.setEnabled(true);
					txtMaskA.setEnabled(true);
					txtRedB.setEnabled(true);
					txtMaskB.setEnabled(true);
					txtRedC.setEnabled(true);
					txtMaskC.setEnabled(true);
					txtRedD.setEnabled(true);
					txtMaskD.setEnabled(true);
					txtRedE.setEnabled(true);
					txtMaskE.setEnabled(true);
					txtRedF.setEnabled(true);
					txtMaskF.setEnabled(true);
					txtRedG.setEnabled(true);
					txtMaskG.setEnabled(true);
				}
			}
		});
		btnNuevo.setBounds(64, 491, 122, 39);
		pnBody.add(btnNuevo);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(637, 491, 122, 39);
		pnBody.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("Sistema para calcular la mejor ruta");
		lblNewLabel.setBounds(257, 33, 233, 20);
		pnBody.add(lblNewLabel);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEnrutamiento frmAux = new FrmEnrutamiento(redes, mascaraRedes, gateways);
				frmAux.setVisible(true);
				//pnTopologia
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(431, 491, 122, 39);
		pnBody.add(btnSiguiente);
		
		inicio();
		
		
	}

	private void inicio() {
		
		btnComprobar.setEnabled(false);
		lblIpGatewayRedA.setVisible(false);
		lblIpGatewayRedC.setVisible(false);
		lblIpGatewayRedD.setVisible(false);
		lblIpGatewayRedG.setVisible(false);
		
		txtRedA.setText("");
		txtRedA.setEnabled(false);
		txtMaskA.setText("");
		txtMaskA.setEnabled(false);
		
		txtRedB.setText("");
		txtRedB.setEnabled(false);
		txtMaskB.setText("");
		txtMaskB.setEnabled(false);
		
		txtRedC.setText("");
		txtRedC.setEnabled(false);
		txtMaskC.setText("");
		txtMaskC.setEnabled(false);
		
		txtRedD.setText("");
		txtRedD.setEnabled(false);
		txtMaskD.setText("");
		txtMaskD.setEnabled(false);
		
		txtRedE.setText("");
		txtRedE.setEnabled(false);
		txtMaskE.setText("");
		txtMaskE.setEnabled(false);
		
		txtRedF.setText("");
		txtRedF.setEnabled(false);
		txtMaskF.setText("");
		txtMaskF.setEnabled(false);
		
		txtRedG.setText("");
		txtRedG.setEnabled(false);
		txtMaskG.setText("");
		txtMaskG.setEnabled(false);
	}
	
	private boolean camposLlenos() {
		boolean aux = true;
		if( txtRedA.getText().equalsIgnoreCase("") || txtMaskA.getText().equalsIgnoreCase("")
				|| txtRedB.getText().equalsIgnoreCase("") || txtMaskB.getText().equalsIgnoreCase("")
				|| txtRedC.getText().equalsIgnoreCase("") || txtMaskC.getText().equalsIgnoreCase("")
				|| txtRedD.getText().equalsIgnoreCase("") || txtMaskD.getText().equalsIgnoreCase("")
				|| txtRedE.getText().equalsIgnoreCase("") || txtMaskE.getText().equalsIgnoreCase("")
				|| txtRedF.getText().equalsIgnoreCase("") || txtMaskF.getText().equalsIgnoreCase("")
				|| txtRedG.getText().equalsIgnoreCase("") || txtMaskG.getText().equalsIgnoreCase("")) {
			aux = false;
		}
		return aux;
	}
	
	private int calcularIpGateway(JTextField red) {
		
		int cantPuntos = 0;
		int aux = 0;
		String redCalcular = red.getText();
		String direccionIp ="";
		int direccionIpInt;
		
		char[] chars = redCalcular.toCharArray();
		for(char ch: chars) {
			if(ch=='.') {
				cantPuntos++;
			}
			if(cantPuntos>2) {
				if(aux==0) {
					aux++;
				}else {
					direccionIp = direccionIp+""+ch;
				}
			}
		}
		direccionIpInt = Integer.parseInt(direccionIp);
		direccionIpInt++;
		
		return direccionIpInt;
		
	}
}
