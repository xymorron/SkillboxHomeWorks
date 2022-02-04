import java.util.StringJoiner;

public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {

        //TODO: напишите метод генерации массива температур пациентов
        float[] temperatures = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {
            temperatures[i] = Math.round((Math.random() * 8 + 32) * 10) / 10f;
        }
        return temperatures;
    }

    public static boolean isHealthy(float temperature) {
        float deviation = 0.00001f;
        if (36.2 - temperature < deviation && temperature - 36.9 < deviation) {
            return true;
        }
        return false;
    }


    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */
        int patientsCount = temperatureData.length;
        StringJoiner tempJoiner = new StringJoiner(" ");
        float totalTemp = 0;
        int healtyCount = 0;
        float avgTemp;
        for (float patientTemp : temperatureData) {
            tempJoiner.add(Float.toString(patientTemp));
            totalTemp += patientTemp;
            if (isHealthy(patientTemp)) {
                healtyCount++;
            }

        }
        avgTemp = Math.round(totalTemp / patientsCount * 100) / 100f;
        String report =
                "Температуры пациентов: " + tempJoiner.toString() +
                        "\nСредняя температура: " + avgTemp +
                        "\nКоличество здоровых: " + healtyCount;

        return report;
    }
}
