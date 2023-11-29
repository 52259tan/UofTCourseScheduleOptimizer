package InputViewDemo;
import gui.CourseController;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class AutoSuggest extends JPanel{

    JButton addcourse = new JButton();
    private final JTextField tf;
    private final JComboBox combo = new JComboBox();
    private final Vector<String> v = new Vector<String>();
    private final CourseController controller;

    private final List<JTextField> courseFields = new ArrayList<>();
    private final JPanel coursePanel;
    public AutoSuggest(CourseController controller) throws IOException {
        this.controller = controller;
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
                        v.addElement(text);
                        Collections.sort(v);
                        setModel(getSuggestedModel(v, text), text);
                    }
                    hide_flag = true;
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
        String[] countries = AvailableCourses.getAvailableCourses();

        for(int i=0;i<countries.length;i++){
            v.addElement(countries[i]);
        }
        setModel(new DefaultComboBoxModel(v), "");
        JPanel leftPanel = new JPanel(new GridLayout(3,1));
        leftPanel.setBorder(BorderFactory.createTitledBorder("AutoSuggestion Box"));
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
        courseField.setFont(new Font("Arial", Font.BOLD,16));
        courseFields.add(courseField);
        courseField.setMaximumSize(new Dimension(Integer.MAX_VALUE, courseField.getPreferredSize().height));

        coursePanel.add(courseField);
        coursePanel.revalidate();
        coursePanel.repaint();

        // Scroll to the bottom after updating the panel
        //scrollToBottom();
    }

    private JPanel createCoursesButton() {
        JButton addButton = createButton("Add Another Course", this::addButtonClicked);
        JButton submitButton = createButton("Submit", this::submitButtonClicked);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(submitButton);

        return buttonPanel;
    }

    private JPanel createMapsButton(){
        JButton map1 = createButton("Mon. Map", this::map1Clicked);
        JButton map2 = createButton("Tues. Map", this::map2Clicked );
        JButton map3 = createButton("Wed. Map", this::map3Clicked);
        JButton map4 = createButton("Thur. Map", this::map4Clicked);
        JButton map5 = createButton("Fri. Map", this::map5Clicked);

        JPanel mapsPanel = new JPanel(new GridLayout(1, 5));
        mapsPanel.add(map1);
        mapsPanel.add(map2);
        mapsPanel.add(map3);
        mapsPanel.add(map4);
        mapsPanel.add(map5);

        return mapsPanel;
    }

    private void map1Clicked(ActionEvent a){

    }
    private void map2Clicked(ActionEvent u){

    }
    private void map3Clicked(ActionEvent r){

    }
    private void map4Clicked(ActionEvent o){

    }
    private void map5Clicked(ActionEvent r){

    }

    private void addButtonClicked(ActionEvent e){
        addCourseField();
    }
    private void submitButtonClicked(ActionEvent e){
        JButton sourceButton = (JButton) e.getSource(); // Get the button that was clicked
        sourceButton.setBackground(Color.GREEN);
        sourceButton.setForeground(Color.WHITE);
        sourceButton.setOpaque(true);
        sourceButton.setBorderPainted(false);
        sourceButton.setContentAreaFilled(true);

        List<String> courses = new ArrayList<>();
        for (JTextField courseField : courseFields) {
            String courseCode = courseField.getText().trim();
            if (!courseCode.isEmpty()) {
                courses.add(courseCode);
            }
        }

        controller.execute(courses);
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


    public static void main(String[] args) throws IOException {
    }
}