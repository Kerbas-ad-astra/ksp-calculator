package kspcalc;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.*;

import javax.help.*;
import javax.help.CSH.DisplayHelpFromSource;

import kspcal.utils.Constants;
import kspcalc.dialogs.AboutDialog;
import kspcalc.dialogs.ConfigDialog;
import kspcalc.stagecalc.StageCalc;
import kspcalc.stagecalc.StageCalculator;
import kspcalc.stagecalc.StageCalculatorVanilla;




/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Calculator extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3573861286066173188L;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JTabbedPane jTabbedPane1;
	private CircularOrbitalCalculatorPanel orbitalCalculatorPanel1;
	private HohmannTransferCalculatorPanel hohmannTransferCalculatorPanel1;
	private EllipticOrbitCalculatorPanel ellipticOrbitCalculatorPanel1;
	private LaunchOrbitCalculatorPanel orbitLaunchCalculatorPanel1;
	private OrbitDisplayFrame orbitDisplay;
	private StagingCalculatorPanel stagingCalculatorPanel1;
	private StageCalc stageCalculator1;
	private JMenu Help;
	private AboutDialog aboutDialog;
	private AbstractAction AboutAction;
	private JMenuItem AboutItem;
	private JMenuItem HelpItem;
	private JMenuBar jMenuBar1;
	
	private Calculator frame;
	private ImageIcon icon;
	private AbstractAction closeAction1;
	private JMenuItem CloseItem;
	private JSeparator jSeparator1;
	private AbstractAction ConfigurationAction;
	private JMenuItem ConfigItem;
	private JMenu Calc;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Calculator inst = new Calculator();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Calculator() {
		super();
		initGUI();
		orbitDisplay = new OrbitDisplayFrame(100, 100);
		orbitDisplay.setLocationRelativeTo(null);
		orbitDisplay.setVisible(false);
		frame = this;
		try {
			icon = Constants.getIcon();
			this.setIconImage(icon.getImage());
			orbitDisplay.setIconImage(icon.getImage());
		} catch (NullPointerException e) {}
		orbitDisplay.setTitle("KSP Calculator - Orbit Display");
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("KSP Calculator");
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				jMenuBar1.add(getCalc());
				jMenuBar1.add(Box.createHorizontalGlue());
				{
					Help = new JMenu();
					jMenuBar1.add(Help);
					Help.setText("?");
					Help.setToolTipText("Help Menu");
					{
						HelpItem = startJavaHelp();
						Help.add(HelpItem);
					}
					{
						AboutItem = new JMenuItem();
						Help.add(AboutItem);
						AboutItem.setText("About");
						AboutItem.setAction(getAboutAction());
					}
				}
			}
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(400, 330));
				{
					orbitalCalculatorPanel1 = new CircularOrbitalCalculatorPanel(this);
					jTabbedPane1.addTab("Circular Orbit", null, orbitalCalculatorPanel1, null);
				}
				{
					ellipticOrbitCalculatorPanel1 = new EllipticOrbitCalculatorPanel(this);
					jTabbedPane1.addTab("Elliptic Orbit", null, ellipticOrbitCalculatorPanel1, null);
				}
				{
					hohmannTransferCalculatorPanel1 = new HohmannTransferCalculatorPanel(this);
					jTabbedPane1.addTab("Hohmann Transfer Orbit", null, hohmannTransferCalculatorPanel1, null);
				}
				{
					orbitLaunchCalculatorPanel1 = new LaunchOrbitCalculatorPanel();
					jTabbedPane1.addTab("Launch Orbit", null, orbitLaunchCalculatorPanel1, null);
					jTabbedPane1.addTab("Stage Calculator", null, (JPanel) getStageCalculator1(), null);
					jTabbedPane1.addTab("Multi Stage Calculator", null, getStagingCalculatorPanel1(), null);
				}
			}
			pack();
			this.setSize(476, 411);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public void setOrbitDisplay(double apo, double peri, boolean hohmann) {
		orbitDisplay.setOrbits(apo, peri, hohmann);
	}
	
	private AbstractAction getAboutAction() {
		if(AboutAction == null) {
			AboutAction = new AbstractAction("About", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 952771346385077936L;

				public void actionPerformed(ActionEvent evt) {
					frame.getAboutDialog().setVisible(true);
				}
			};
		}
		return AboutAction;
	}
	
	public JDialog getAboutDialog() {
		if(aboutDialog == null) {
			aboutDialog = new AboutDialog(this);
		}
		return aboutDialog;
	}

	private JMenuItem startJavaHelp() {
		JMenuItem mi = null;
		try {
			HelpSet hs = this.getHelpSet("help/help.hs");
			hs.setTitle("KSP Calculator - Help");
			HelpBroker hb = hs.createHelpBroker();
			mi = new JMenuItem("Help");
			DisplayHelpFromSource csh = new CSH.DisplayHelpFromSource(hb);
			mi.addActionListener(csh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mi;
	}
	
	public HelpSet getHelpSet(String helpsetfile) {
	      HelpSet hs = null;
	      ClassLoader cl = this.getClass().getClassLoader();
	      try {
	        URL hsURL = HelpSet.findHelpSet(cl, helpsetfile);
	        hs = new HelpSet(null, hsURL);
	      } catch(Exception ee) {
	        System.out.println("HelpSet: "+ee.getMessage());
	        System.out.println("HelpSet: "+ helpsetfile + " not found");
	      }
	      return hs;
	   }
	
	private StageCalc getStageCalculator1() {
		if(stageCalculator1 == null) {
			stageCalculator1 = StageCalculator.getStageCalculator();
		}
		return stageCalculator1;
	}
	
	private StagingCalculatorPanel getStagingCalculatorPanel1() {
		if(stagingCalculatorPanel1 == null) {
			stagingCalculatorPanel1 = new StagingCalculatorPanel(this);
		}
		return stagingCalculatorPanel1;
	}
	
	private JMenu getCalc() {
		if(Calc == null) {
			Calc = new JMenu();
			Calc.setText("Calculator");
			Calc.add(getConfigItem());
			Calc.add(getJSeparator1());
			Calc.add(getCloseItem());
		}
		return Calc;
	}
	
	private JMenuItem getConfigItem() {
		if(ConfigItem == null) {
			ConfigItem = new JMenuItem();
			ConfigItem.setText("Calculator");
			ConfigItem.setAction(getConfigurationAction());
		}
		return ConfigItem;
	}
	
	private AbstractAction getConfigurationAction() {
		if(ConfigurationAction == null) {
			ConfigurationAction = new AbstractAction("Configuration", null) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 8848019343344314900L;

				public void actionPerformed(ActionEvent evt) {
					ConfigDialog config = new ConfigDialog(frame);
					config.setVisible(true);
				}
			};
		}
		return ConfigurationAction;
	}
	
	private JSeparator getJSeparator1() {
		if(jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}
	
	private JMenuItem getCloseItem() {
		if(CloseItem == null) {
			CloseItem = new JMenuItem();
			CloseItem.setText("Close");
			CloseItem.setAction(getCloseAction1());
		}
		return CloseItem;
	}
	
	private AbstractAction getCloseAction1() {
		if(closeAction1 == null) {
			closeAction1 = new AbstractAction("Close", null) {
				public void actionPerformed(ActionEvent evt) {
					System.exit(0);
				}
			};
		}
		return closeAction1;
	}

}
