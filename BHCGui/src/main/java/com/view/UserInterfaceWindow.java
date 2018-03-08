package com.view;

import com.model.Trip;
import com.service.BookingDay;
import com.service.Rates;
import com.service.RatesSocket;
import com.utils.DateUtils;
import com.utils.InvalidDateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class UserInterfaceWindow {

    private JFrame frame;
    private Trip trip;
    private Boolean pressedBtn = false;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserInterfaceWindow window = new UserInterfaceWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initilize the GUI
     */
    public UserInterfaceWindow() {
        initialize();
    }


    private void initialize() {

        //Create JFrame object to place all other containters inside of
        trip = new Trip();
        frame = new JFrame("BHC");
        frame.setBounds(100, 100, 600, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tripPanel = new JPanel();
        tripPanel.setBackground(Color.decode("#0066FF"));

        frame.getContentPane().add(tripPanel, BorderLayout.NORTH);
        tripPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        Label label = new Label("Choose Trip: ");
        tripPanel.add(label);

        //Enable drop menu to choose hiking trail
        JComboBox hikingTrailsBox = new JComboBox(Rates.HIKE.values());
        tripPanel.add(hikingTrailsBox);

        JPanel datePanel = new JPanel();
        frame.getContentPane().add(datePanel, BorderLayout.WEST);
        datePanel.setLayout(new GridLayout(0, 2, 0, 0));

        //Display the Months
        Label monthLabel = new Label("Month");
        monthLabel.setFont(new Font("Eras Demi ITC", Font.BOLD, 13));
        monthLabel.setAlignment(Label.CENTER);
        datePanel.add(monthLabel);

        JComboBox monthDropDown = new JComboBox(DateUtils.getMonths());
        datePanel.add(monthDropDown);


        //Display the Years
        Label yearLabel = new Label("Year");
        yearLabel.setFont(new Font("Eras Demi ITC", Font.BOLD, 13));
        yearLabel.setAlignment(Label.CENTER);
        datePanel.add(yearLabel);

        JComboBox yearDropDown = new JComboBox(DateUtils.getYears());
        datePanel.add(yearDropDown);

        //Display the Days
        Label dayLabel = new Label("Day");
        dayLabel.setFont(new Font("Eras Demi ITC", Font.BOLD, 13));
        dayLabel.setAlignment(Label.CENTER);
        datePanel.add(dayLabel);

        JComboBox dayDropDown = new JComboBox(DateUtils.getDays(trip.getYear(),trip.getMonth()).toArray());
        //dayDropDown.setEnabled(false);
        datePanel.add(dayDropDown);

        //Have Buttons available according to the trip
        JPanel durationPanel = new JPanel();
        durationPanel.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(durationPanel, BorderLayout.CENTER);
        durationPanel.setLayout(new BorderLayout(0, 0));

        Label durationLabel = new Label("Trip Duration");
        durationLabel.setFont(new Font("Eras Demi ITC", Font.BOLD, 12));

        durationLabel.setAlignment(Label.CENTER);
        durationPanel.add(durationLabel, BorderLayout.NORTH);

        JPanel durationBtnPanel = new JPanel();
        durationPanel.add(durationBtnPanel, BorderLayout.CENTER);
        durationBtnPanel.setLayout(new BorderLayout(0, 0));


        //Duration buttons and their values
        JButton threeDayBtn = new JButton("3 Days");
        durationBtnPanel.add(threeDayBtn, BorderLayout.NORTH);

        JButton fourDayBtn = new JButton("4 Days");
        durationBtnPanel.add(fourDayBtn, BorderLayout.WEST);

        JButton sevenDayBtn = new JButton("7 Days");
        durationBtnPanel.add(sevenDayBtn, BorderLayout.CENTER);

        JButton fiveDayBtn = new JButton("5 Days");
        durationBtnPanel.add(fiveDayBtn, BorderLayout.EAST);

        JButton twoDayBtn = new JButton("2 Days");
        durationBtnPanel.add(twoDayBtn, BorderLayout.SOUTH);

        //Panel to output the cost of the trip
        JPanel costPanel = new JPanel();
        costPanel.setPreferredSize(new Dimension(100, 100));
        frame.getContentPane().add(costPanel, BorderLayout.EAST);
        costPanel.setLayout(new BorderLayout(0, 0));

        Label costOutput = new Label("");
        costOutput.setAlignment(Label.CENTER);
        costPanel.add(costOutput, BorderLayout.CENTER);

        Label costTitleLabel = new Label("Cost");
        costTitleLabel.setFont(new Font("Eras Demi ITC", Font.BOLD, 12));
        costTitleLabel.setAlignment(Label.CENTER);
        costPanel.add(costTitleLabel, BorderLayout.NORTH);

        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(Color.decode("#0066FF"));
        frame.getContentPane().add(submitPanel, BorderLayout.SOUTH);


        //Submit button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Eras Demi ITC", Font.BOLD, 11));
        submitPanel.add(btnSubmit);

        JButton resetBtn = new JButton("Reset");
        submitPanel.add(resetBtn);
        disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);

        btnSubmit.setEnabled(false);



        //Clear every field and allow user to reset
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmit.setEnabled(false);
                hikingTrailsBox.setSelectedItem(Rates.HIKE.GARDINER);
                monthDropDown.setSelectedItem(DateUtils.getJanMonth());
                yearDropDown.setSelectedItem("2018");
                dayDropDown.setSelectedItem("1");
                costOutput.setText("");
                trip.setTripName(Rates.HIKE.GARDINER);
                disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);

            }
        });


        //Select 3 days duration button
        threeDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmit.setEnabled(true);


                if(pressedBtn == true){
                    disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);
                }
                trip.setDuration(3);
                pressedBtn = true;
                threeDayBtn.setEnabled(false);
                threeDayBtn.setBackground(Color.GREEN);


            }
        });

        //Select 4 days duration button
        fourDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmit.setEnabled(true);

                if(pressedBtn == true){
                    disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);
                }
                trip.setDuration(4);
                pressedBtn = true;
                fourDayBtn.setEnabled(false);
                fourDayBtn.setBackground(Color.GREEN);




            }
        });

        //Select 5 days duration button
        fiveDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmit.setEnabled(true);

                if(pressedBtn == true){
                    disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);
                }
                trip.setDuration(5);
                pressedBtn = true;
                fiveDayBtn.setEnabled(false);
                fiveDayBtn.setBackground(Color.GREEN);




            }
        });

        //Select 2 days duration button
        twoDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmit.setEnabled(true);

                if(pressedBtn == true){
                    disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);
                }
                trip.setDuration(2);
                pressedBtn = true;
                twoDayBtn.setEnabled(false);
                twoDayBtn.setBackground(Color.GREEN);


            }
        });

        //Select 7 days duration button
        sevenDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmit.setEnabled(true);

                if(pressedBtn == true){
                    disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);
                }
                trip.setDuration(7);
                pressedBtn = true;
                sevenDayBtn.setEnabled(false);
                sevenDayBtn.setBackground(Color.GREEN);

            }
        });


        //Select Month
        monthDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox monthComboBox = (JComboBox) e.getSource();
                Object selected = monthComboBox.getSelectedItem();
                trip.setMonthName(selected.toString());
                trip.setMonth(monthComboBox.getSelectedIndex() + 1);

                //call to update days
                dayDropDown.setModel(new DefaultComboBoxModel(DateUtils.getDays(trip.getYear(),trip.getMonth()).toArray()));

             }
        });

        //Select Year
        yearDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox yearComboBox = (JComboBox) e.getSource();
                Object yearSelected =  yearComboBox.getSelectedItem();

                try {
                    trip.setYear(Integer.parseInt(yearSelected.toString()));
                }catch(NumberFormatException ex){
                    //Error parsing, please rest and try again
                }
                //call to update days
                dayDropDown.setModel(new DefaultComboBoxModel(DateUtils.getDays(trip.getYear(),trip.getMonth()).toArray()));

            }
        });

        //Select Hiking trail
        hikingTrailsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JComboBox trailOption = (JComboBox) e.getSource();
                Object trailSelected = trailOption.getSelectedItem();
                trip.setTripName(Rates.HIKE.valueOf(trailSelected.toString()));

                disableDurationButtons(trip,twoDayBtn,threeDayBtn,fourDayBtn,fiveDayBtn,sevenDayBtn);


            }
        });

        //Submit button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //submitForm(monthDropDown,yearDropDown,dayDropDown,costOutput);

                RatesSocket connection = new RatesSocket();

                HashMap<Integer,String> result = connection.connection(trip.getTripName(),trip.getDuration(),trip.getYear(),trip.getMonth(),trip.getDay());

               //Error getting results
               if(result.containsKey(0)){
                   JOptionPane.showMessageDialog(null,result.get(0),"Reset And Try Again" , JOptionPane.WARNING_MESSAGE);

               }else if (result.containsKey(1)){
                   costOutput.setText("$ " + result.get(1));
                   costOutput.setFont(new Font("Eras Demi ITC", Font.BOLD, 13));
                   costOutput.setForeground(Color.BLUE);
               }

                btnSubmit.setEnabled(false);
            }
        });

    }


    /**
     * Submit button action, does the validation for the date and trip entered before outputing cost
     * @param monthDropDown
     * @param yearDropDown
     * @param dayDropDown
     * @param costOutput
     */

    private void submitForm(JComboBox monthDropDown, JComboBox yearDropDown, JComboBox dayDropDown,Label costOutput){
        BookingDay startDay = new BookingDay(trip.getYear(),trip.getMonth(),trip.getDay());
        Rates rates = new Rates(trip.getTripName());
        rates.setBeginDate(startDay);

        boolean success = rates.setDuration(trip.getDuration());

        //Try catch to check if date is valid, catch an invalid date exception
        try{
            if(rates.isValidDates()){
                costOutput.setText("$ " + String.valueOf(rates.getCost()));
                costOutput.setFont(new Font("Eras Demi ITC", Font.BOLD, 13));
                costOutput.setForeground(Color.BLUE);
            }else{
                throw new InvalidDateException(rates.getDetails());
            }
        }catch(InvalidDateException ex){

            //If errors caught, then give user another chance to input correct date. Repeat until valid date entered
            JOptionPane.showMessageDialog(null,ex.getMessage() + "\n" + "Start of Season: June 1st\n" + "End of Season: Oct 1st"
                    + "\n" + "Please Reselect Dates...", "Invalid Date", JOptionPane.WARNING_MESSAGE);


            JComboBox months = new JComboBox(DateUtils.getMonths());
            JComboBox years = new JComboBox(DateUtils.getYears());
            JComboBox days = new JComboBox(DateUtils.getDays(trip.getYear(),trip.getMonth()).toArray());

            months.setEditable(true);

            JOptionPane.showMessageDialog( null, months, "Select Month", JOptionPane.QUESTION_MESSAGE);
            trip.setMonthName(months.getSelectedItem().toString());
            trip.setMonth(months.getSelectedIndex() + 1);

            JOptionPane.showMessageDialog( null, years, "Select Year", JOptionPane.QUESTION_MESSAGE);
            try {
                trip.setYear(Integer.parseInt(years.getSelectedItem().toString()));
            }catch(Exception parseEx){
                //Error parsing, please rest and try again
                JOptionPane.showMessageDialog( null,null, "Error Parsing", JOptionPane.WARNING_MESSAGE);
            }

            JOptionPane.showMessageDialog( null, days, "Select Day", JOptionPane.QUESTION_MESSAGE);
            trip.setDay(days.getSelectedIndex() + 1 );
            monthDropDown.setSelectedItem(months.getSelectedItem());
            yearDropDown.setSelectedItem(years.getSelectedItem());
            dayDropDown.setSelectedItem(days.getSelectedItem());



            submitForm(monthDropDown,yearDropDown,dayDropDown,costOutput);
        }



    }


    /**
     * Function that resets the active buttons depending on the hiking trail selected
     * @param trip
     * @param twoDayBtn
     * @param threeDayBtn
     * @param fourDayBtn
     * @param fiveDayBtn
     * @param sevenDayBtn
     */
    private void disableDurationButtons(Trip trip, JButton twoDayBtn, JButton threeDayBtn, JButton fourDayBtn, JButton fiveDayBtn, JButton sevenDayBtn) {

        twoDayBtn.setBackground(null);
        fourDayBtn.setBackground(null);
        sevenDayBtn.setBackground(null);
        threeDayBtn.setBackground(null);
        fiveDayBtn.setBackground(null);


        if (trip.getTripName().equals(Rates.HIKE.GARDINER)){
            twoDayBtn.setEnabled(false);
            fourDayBtn.setEnabled(false);
            sevenDayBtn.setEnabled(false);
            threeDayBtn.setEnabled(true);
            fiveDayBtn.setEnabled(true);
        }if(trip.getTripName().equals(Rates.HIKE.HELLROARING)){
            twoDayBtn.setEnabled(true);
            fourDayBtn.setEnabled(true);
            sevenDayBtn.setEnabled(false);
            threeDayBtn.setEnabled(true);
            fiveDayBtn.setEnabled(false);

        }
        if(trip.getTripName().equals(Rates.HIKE.BEATEN)){
            twoDayBtn.setEnabled(false);
            fourDayBtn.setEnabled(false);
            sevenDayBtn.setEnabled(true);
            threeDayBtn.setEnabled(false);
            fiveDayBtn.setEnabled(true);
        }


    }
}
