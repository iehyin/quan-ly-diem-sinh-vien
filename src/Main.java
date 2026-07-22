//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import control.BusinessControl;
import repository.DataStorage;
import ui.ConsoleUI;
import entity.MonHoc;

public class Main {
    public static void main(String[] args) {
        BusinessControl control = new BusinessControl();
        ConsoleUI ui = new ConsoleUI(control);
        ui.start();
    }
}