package gui;
import ErrorView.ErrorView;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class CourseInputView extends JPanel{
    /**
     * This is course input view
     */
    private final JTextField tf;
    private final JComboBox combo = new JComboBox();
    private final Vector<String> v = new Vector<String>();
    private final CourseController controller;
    private List<JTextField> courseFields = new ArrayList<>();
    private final JPanel coursePanel;
    private final String[] allCourses;

    JButton map1;
    JButton map2;
    JButton map3;
    JButton map4;
    JButton map5;

    public CourseInputView(CourseController controller) throws IOException {
        this.controller = controller;
        allCourses = AvailableCourses.getAvailableCourses();

        for(int i=0;i<allCourses.length;i++){
            v.addElement(allCourses[i]);
        }
        setLayout(new GridLayout(1,2));
        combo.setEditable(true);
        tf = (JTextField) combo.getEditor().getEditorComponent();
        tf.setFont(new Font("Arial", Font.PLAIN, 16));
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        String text = tf.getText().toUpperCase();
                        if(text.length()==0) {
                            combo.hidePopup();
                            setModel(new DefaultComboBoxModel(v), "");
                        }else{
                            DefaultComboBoxModel m = getSuggestedModel(v, text);
                            if(m.getSize()==0 || hide_flag) {
                                combo.hidePopup();
                                hide_flag = false;
                            }else{
                                setModel(m, text);
                                combo.showPopup();
                            }
                        }
                    }
                });
            }
            public void keyPressed(KeyEvent e) {
                String text = tf.getText().toUpperCase();
                int code = e.getKeyCode();
                if(code==KeyEvent.VK_ENTER) {
                    if(!v.contains(text)) {
                        //v.addElement(text);
                        Collections.sort(v);
                        setModel(getSuggestedModel(v, text), text);
                    }
                    hide_flag = true;
                    if (Arrays.asList(allCourses).contains(tf.getText().toUpperCase())){
                        addCourseField();}
                    else{
                        ErrorView.ErrorViewInvalidCourseCode(tf.getText());
                    }
                }else if(code==KeyEvent.VK_ESCAPE) {
                    hide_flag = true;
                }else if(code==KeyEvent.VK_RIGHT) {
                    for(int i=0;i<v.size();i++) {
                        String str = v.elementAt(i);
                        if(str.startsWith(text)) {
                            combo.setSelectedIndex(-1);
                            tf.setText(str.toUpperCase());
                            return;
                        }
                    }
                }
            }
        });

        setModel(new DefaultComboBoxModel(v), "");
        JPanel leftPanel = new JPanel(new GridLayout(3,1));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Input yo courses here"));
        leftPanel.add(combo, BorderLayout.NORTH);

        leftPanel.add(createCoursesButton());

        leftPanel.add(createMapsButton());


        coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.setBackground(Color.WHITE);


        add(leftPanel);
        add(coursePanel);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setPreferredSize(new Dimension(1000, 400));
    }
    private void addCourseField() {
        JTextField courseField = new JTextField(40);
        courseField.setText(tf.getText().toUpperCase());
        tf.setText("");
        courseField.setFont(new Font("Arial", Font.BOLD,16));
        courseFields.add(courseField);
        courseField.setMaximumSize(new Dimension(Integer.MAX_VALUE, courseField.getPreferredSize().height));

        coursePanel.add(courseField);
        coursePanel.revalidate();
        coursePanel.repaint();
    }

    private JPanel createCoursesButton() {
        JButton addAnotherButton = createButton("Add Another Course", this::addButtonClicked);
        JButton submitButton = createButton("Submit", this::submitButtonClicked);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addAnotherButton);
        buttonPanel.add(submitButton);

        return buttonPanel;
    }

    private JPanel createMapsButton(){
        map1 = createButton("Mon. Map", this::map1Clicked);
        map2 = createButton("Tues. Map", this::map2Clicked );
        map3 = createButton("Wed. Map", this::map3Clicked);
        map4 = createButton("Thur. Map", this::map4Clicked);
        map5 = createButton("Fri. Map", this::map5Clicked);

        map1.setEnabled(false);
        map2.setEnabled(false);
        map3.setEnabled(false);
        map4.setEnabled(false);
        map5.setEnabled(false);

        JPanel mapsPanel = new JPanel(new GridLayout(1, 5));
        mapsPanel.add(map1);
        mapsPanel.add(map2);
        mapsPanel.add(map3);
        mapsPanel.add(map4);
        mapsPanel.add(map5);

        return mapsPanel;
    }

    private void map1Clicked(ActionEvent a) {
        // Step 1: Load the image
        ImageIcon imageIcon = new ImageIcon("mapimgs/map_MON.png");

        // Step 2: Set the image to a JLabel
        JLabel label = new JLabel();
        label.setIcon(imageIcon);

        // Step 3: Create and display a custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Monday");
        dialog.add(label);
        dialog.pack(); // Adjusts the dialog size
        dialog.setLocationRelativeTo(null); // Center the dialog on screen
        dialog.setVisible(true);
    }

    private void map2Clicked(ActionEvent u){
        // Step 1: Load the image
        ImageIcon imageIcon = new ImageIcon("mapimgs/map_TUE.png"); // Provide the path to your image file

        // Step 2: Set the image to a JLabel
        JLabel label = new JLabel();
        label.setIcon(imageIcon);

        // Step 3: Create and display a custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Tuesday");
        dialog.add(label);
        dialog.pack(); // Adjusts the dialog size
        dialog.setLocationRelativeTo(null); // Center the dialog on screen
        dialog.setVisible(true);

    }
    private void map3Clicked(ActionEvent r){
        // Step 1: Load the image
        ImageIcon imageIcon = new ImageIcon("mapimgs/map_WED.png"); // Provide the path to your image file

        // Step 2: Set the image to a JLabel
        JLabel label = new JLabel();
        label.setIcon(imageIcon);

        // Step 3: Create and display a custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Wednesday");
        dialog.add(label);
        dialog.pack(); // Adjusts the dialog size
        dialog.setLocationRelativeTo(null); // Center the dialog on screen
        dialog.setVisible(true);

    }
    private void map4Clicked(ActionEvent o){
        // Step 1: Load the image
        ImageIcon imageIcon = new ImageIcon("mapimgs/map_THU.png"); // Provide the path to your image file

        // Step 2: Set the image to a JLabel
        JLabel label = new JLabel();
        label.setIcon(imageIcon);

        // Step 3: Create and display a custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Thursday");
        dialog.add(label);
        dialog.pack(); // Adjusts the dialog size
        dialog.setLocationRelativeTo(null); // Center the dialog on screen
        dialog.setVisible(true);

    }
    private void map5Clicked(ActionEvent r){
        // Step 1: Load the image
        ImageIcon imageIcon = new ImageIcon("mapimgs/map_FRI.png"); // Provide the path to your image file

        // Step 2: Set the image to a JLabel
        JLabel label = new JLabel();
        label.setIcon(imageIcon);

        // Step 3: Create and display a custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Friday");
        dialog.add(label);
        dialog.pack(); // Adjusts the dialog size
        dialog.setLocationRelativeTo(null); // Center the dialog on screen
        dialog.setVisible(true);

    }
    private void addButtonClicked(ActionEvent e){
        addCourseField();
    }
    private void submitButtonClicked(ActionEvent e){
        // Get rid of empty strings
        List<JTextField> place_holder = new ArrayList<>();
        for (JTextField courseCode: courseFields){
            if (!courseCode.getText().trim().isEmpty()){
                place_holder.add(courseCode);
            }
        }
        courseFields = place_holder;
        if (courseFields.isEmpty()){
            ErrorView.ErrorViewNoInput();
            return;
        }
        // Get a list of invalid course codes, empty list if none.
        List<String> invalidCodes = new ArrayList<>();
        for (JTextField courseCode: courseFields){
            if (!Arrays.asList(allCourses).contains(courseCode.getText())){
                invalidCodes.add(courseCode.getText());
            }
        }
        // If there are no invalid course codes, check if there are both Fall or Winter term in the input.
        if (invalidCodes.size()==0){
            Set<String> hashSet = new HashSet<>();
            for (JTextField courseCode: courseFields){
                hashSet.add(String.valueOf(courseCode.getText().charAt(courseCode.getText().length() - 1)));
            }
            if (hashSet.contains("F") && hashSet.contains("S")){
                ErrorView.ErrorViewTermCodeMismatch();
            }
            else{
                List<String> courses = new ArrayList<>();
                for (JTextField courseField : courseFields) {
                    String courseCode = courseField.getText().trim();
                    if (!courseCode.isEmpty()) {
                        courses.add(courseCode);
                    }
                }
                controller.execute(courses);
                map1.setEnabled(true);
                map2.setEnabled(true);
                map3.setEnabled(true);
                map4.setEnabled(true);
                map5.setEnabled(true);}}
        // If invalid course codes exist, stop and throw a pop-up
        else{ErrorView.ErrorViewInvalidCourseCode(invalidCodes);}
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }
    private boolean hide_flag = false;
    private void setModel(DefaultComboBoxModel mdl, String str) {
        combo.setModel(mdl);
        combo.setSelectedIndex(-1);
        tf.setText(str.toUpperCase());
    }
    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for(String s: list) {
            if(s.startsWith(text)) m.addElement(s);
        }
        return m;
    }
}