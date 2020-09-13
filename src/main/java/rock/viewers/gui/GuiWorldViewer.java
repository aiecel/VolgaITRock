package rock.viewers.gui;

import rock.viewers.WorldViewer;
import rock.world.World;

import javax.swing.*;
import java.awt.*;

public class GuiWorldViewer implements WorldViewer {
    private World world;
    private JFrame frame;
    private JLabel healthLabel;
    private JLabel keysLabel;
    private JLabel stepsLabel;
    private WorldJPanel worldPanel;

    @Override
    public void initView(World world) {
        this.world = world;

        //main frame
        frame = new JFrame("Volga IT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        //frame grid
        GridBagConstraints c = new GridBagConstraints();

        //health
        healthLabel = new JLabel("Health: ");

        //keys
        keysLabel = new JLabel("Keys: ");

        //steps
        stepsLabel = new JLabel("Steps: ");

        //upper panel
        JPanel upperPanel = new JPanel();
        upperPanel.add(healthLabel);
        upperPanel.add(keysLabel);
        upperPanel.add(stepsLabel);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        frame.add(upperPanel, c);

        //world panel
        worldPanel = new WorldJPanel(world);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        frame.add(worldPanel,c);

        frame.setMinimumSize(new Dimension(worldPanel.getPreferredSize().width + 20, worldPanel.getPreferredSize().height + 70));
        frame.setVisible(true);
    }

    @Override
    public void updateView() {
        //update health
        healthLabel.setText("Health: " + world.getPlayer().getHealth());

        //update keys
        StringBuilder builder = new StringBuilder().append("Keys: ");
        world.getPlayer().getKeys().forEach(key -> builder.append(key).append(" "));
        keysLabel.setText(builder.toString());

        //update steps
        stepsLabel.setText("Steps: " + world.getPlayer().getDistanceTravelled());

        //update world panel
        worldPanel.repaint();

        frame.revalidate();
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
