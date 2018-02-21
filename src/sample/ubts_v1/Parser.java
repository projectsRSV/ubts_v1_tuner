package sample.ubts_v1;


import javafx.scene.control.Spinner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private List<AmplifModel> paModels;
    private List<Spinner<Double>> spinners;
    private Map<Integer, Double> integerDoubleMap;

    public Parser(List<AmplifModel> paModels) {
        this.paModels = paModels;
        integerDoubleMap = new HashMap<>();
        initMap();
    }

    public void setSpinners(List<Spinner<Double>> spinners) {
        this.spinners = spinners;
    }

    public void parse(String message) {
        List<String> addList = Arrays.asList("PA0_address", "PA1_address", "PA2_address", "PA3_address");
        List<String> bandList = Arrays.asList("PA0_band", "PA1_band", "PA2_band", "PA3_band");
        List<String> actList = Arrays.asList("PA0_isactive", "PA1_isactive", "PA2_isactive", "PA3_isactive");
        List<String> fanList = Arrays.asList("PA0_fanpin", "PA1_fanpin", "PA2_fanpin", "PA3_fanpin");
        try {
            if (message.contains("address") && message.contains("band") && message.contains("fanpin") && message.contains("isactive")) {
                //            System.out.println(message.substring(message.indexOf("_address") - 1, message.indexOf("_address")));
                int i = Integer.parseInt(message.substring(message.indexOf("_address") - 1, message.indexOf("_address")));
                //            System.out.println(i);

                paModels.get(i).setAddressI2C(message.substring(message.indexOf(addList.get(i)) + addList.get(i).length() + 1,
                        message.indexOf(addList.get(i)) + addList.get(i).length() + 3));
                paModels.get(i).setBand(message.substring(message.indexOf(bandList.get(i)) + bandList.get(i).length() + 1,
                        message.indexOf(bandList.get(i)) + bandList.get(i).length() + 3));
                paModels.get(i).setValid(message.substring(message.indexOf(actList.get(i)) + actList.get(i).length() + 1,
                        message.indexOf(actList.get(i)) + actList.get(i).length() + 3));
                paModels.get(i).setFanPin(message.substring(message.indexOf(fanList.get(i)) + fanList.get(i).length() + 1,
                        message.indexOf(fanList.get(i)) + fanList.get(i).length() + 3));
            } else if (message.contains("att_1") || message.contains("att_2") || message.contains("att_3") || message.contains("att_4")) {
                String[] strings = message.split("\\s+");
                int k = 0;
                for (int j = 0; j < strings.length; j++) {
                    try {
                        int intVar = Integer.parseInt(strings[j], 16);
                        double doubleVar = 0;
                        doubleVar = integerDoubleMap.get(intVar & 0xfc);
                        if ((intVar & 0x01) == 0x01) {
                            intVar &= 0xfe;
                            doubleVar += 0.25;
                        }
                        if ((intVar & 0x02) == 0x02) {
                            intVar &= 0xfd;
                            doubleVar += 0.5;
                        }
                        spinners.get(k++).getValueFactory().setValue(doubleVar);
                    } catch (NumberFormatException e) {
//                        System.out.println(e.getClass().getName());
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getClass().getName());
        }
    }

    private void initMap() {
        integerDoubleMap.put(0x04, 1d);
        integerDoubleMap.put(0x08, 2d);
        integerDoubleMap.put(0x0c, 3d);
        integerDoubleMap.put(0x14, 4d);
        integerDoubleMap.put(0x18, 6d);
        integerDoubleMap.put(0x1c, 7d);
        integerDoubleMap.put(0x20, 8d);
        integerDoubleMap.put(0x24, 9d);
        integerDoubleMap.put(0x28, 10d);

        integerDoubleMap.put(0x2c, 11d);
        integerDoubleMap.put(0x30, 12d);
        integerDoubleMap.put(0x34, 13d);
        integerDoubleMap.put(0x38, 14d);
        integerDoubleMap.put(0x3c, 15d);
        integerDoubleMap.put(0x40, 16d);
        integerDoubleMap.put(0x44, 17d);
        integerDoubleMap.put(0x48, 18d);

        integerDoubleMap.put(0x4c, 19d);
        integerDoubleMap.put(0x50, 20d);
        integerDoubleMap.put(0x54, 21d);
        integerDoubleMap.put(0x58, 22d);
        integerDoubleMap.put(0x5c, 23d);
        integerDoubleMap.put(0x60, 24d);
        integerDoubleMap.put(0x64, 25d);
        integerDoubleMap.put(0x68, 26d);

        integerDoubleMap.put(0x6c, 27d);
        integerDoubleMap.put(0x70, 28d);
        integerDoubleMap.put(0x74, 29d);
        integerDoubleMap.put(0x78, 30d);
        integerDoubleMap.put(0x7c, 31d);
    }
}
