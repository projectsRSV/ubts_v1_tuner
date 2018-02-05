package sample.ubts_v1;


import java.util.Arrays;
import java.util.List;

public class Parser {
    private List<AmplifModel> paModels;

    public Parser(List<AmplifModel> paModels) {
        this.paModels = paModels;
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
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(message);
        }
    }
}
