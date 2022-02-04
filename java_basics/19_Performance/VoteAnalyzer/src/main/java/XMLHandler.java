import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
//    private Voter voter = null;
    String name = null;
    String birthDay = null;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private HashMap<Integer, WorkTime> voteStationWorkTimes;
    private DBConnection connection;

    public XMLHandler() {
        voteStationWorkTimes = new HashMap<>();
        connection= new DBConnection();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter") && name == null) {
                birthDay = attributes.getValue("birthDay");
                name = attributes.getValue("name");
            }
            else if (qName.equals("visit") && name != null) {
                DBConnection.countVoter(name, birthDay);
                Date visitTime = visitDateFormat.parse(attributes.getValue("time"));
                int stationId = Integer.parseInt(attributes.getValue("station"));
                if (!voteStationWorkTimes.containsKey(stationId))
                    voteStationWorkTimes.put(stationId, new WorkTime());
                voteStationWorkTimes.get(stationId).addVisitTime(visitTime.getTime());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            name = null;
        }
    }

    public void printVoteStationWorkTimes() {
        System.out.println("Voting station work times: ");
        for(Integer votingStation : voteStationWorkTimes.keySet())
        {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }
    }

}
