package core;

import java.util.ArrayList;
import java.util.List;

public class Line
{
    private String id;
    private String name;
    private List<Station> stations;

    public Line(String number, String name)
    {
        this.id = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public List<Station> getStations()
    {
        return stations;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    //    @Override
//    public int compareTo(Line line)
//    {
//        return Integer.compare(number, line.getNumber());
//    }
//
//    @Override
//    public boolean equals(Object obj)
//    {
//        return compareTo((Line) obj) == 0;
//    }
}