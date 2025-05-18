package study.design.pattern.templatemethod;

import org.junit.jupiter.api.Test;

class DatabaseProcessorTest {

    @Test
    public void test() {
        DatabaseProcessor saveData = new SaveData();
        saveData.execute();

        DatabaseProcessor readData = new ReadData();
        readData.execute();
    }
}