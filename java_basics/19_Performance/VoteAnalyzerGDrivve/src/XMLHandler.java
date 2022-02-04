import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private Voter voter = null;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private HashMap<Voter, Integer> voterCounts;
    private HashMap<Integer, WorkTime> voteStationWorkTimes;

    public XMLHandler() {
        voterCounts = new HashMap<>();
        voteStationWorkTimes = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            }
            else if (qName.equals("visit") && voter != null) {
                int count = voterCounts.getOrDefault(voter, 0);
                voterCounts.put(voter, count + 1);
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
            voter = null;
        }
    }

    public void printDuplicatedVoters() {
        System.out.println("Duplicated voters: ");
        for (Voter voter: voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
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
