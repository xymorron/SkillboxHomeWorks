import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator calculator;


    @Override
    protected void setUp() throws Exception {
        Line[] lines = {new Line(1, "1st Line"),
                        new Line(2, "2nd Line"),
                        new Line(3, "3rd Line")};
        Station[] stations = {new Station("a-1", lines[0]),
                              new Station("b-1", lines[0]),
                              new Station("c-1", lines[0]),
                              new Station("a-2", lines[1]),
                              new Station("b-2", lines[1]),
                              new Station("a-3", lines[2]),
                              new Station("b-3", lines[2])};
        stationIndex = new StationIndex();
        Arrays.stream(lines).forEach(stationIndex::addLine);
        Arrays.stream(stations).forEach(stationIndex::addStation);
        Arrays.stream(stations).forEach(station -> station.getLine().addStation(station));
        stationIndex.addConnection(Arrays.asList(stations[2], stations[3]));
        stationIndex.addConnection(Arrays.asList(stations[4], stations[5]));
        calculator = new RouteCalculator(stationIndex);
    }

    public void testCalculateDurationDirect() {
        Station from = stationIndex.getStation("c-1");
        Station to = stationIndex.getStation("a-1", 1);
        List<Station> route = calculator.getShortestRoute(from, to);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 5;
        assertEquals(expected, actual);
    }

    public void testCalculateDuration1Connection() {
        Station from = stationIndex.getStation("a-1");
        Station to = stationIndex.getStation("b-2");
        List<Station> route = calculator.getShortestRoute(from, to);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 11;
        assertEquals(expected, actual);
    }

    public void testCalculateDuration2Connections() {
        Station from = stationIndex.getStation("a-1");
        Station to = stationIndex.getStation("b-3");
        List<Station> route = calculator.getShortestRoute(from, to);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17;
        assertEquals(expected, actual);
    }
}
