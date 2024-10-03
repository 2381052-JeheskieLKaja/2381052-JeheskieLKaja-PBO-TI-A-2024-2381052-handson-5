import java.util.Scanner;

public class Main {
    public static String[] todos = new String[10];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showTodoList() {
        System.out.println("To-Do List :");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todos[i] != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }

    public static void addTodoList(String todo) {
        resizeArrayIfFull();

        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    private static void resizeArrayIfFull() {
        boolean isFull = isArrayFull();

        if (isFull) {
            ResizeArrayToTwoTimesBigger();
        }
    }

    private static void ResizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    private static boolean isArrayFull() {
        boolean isFull = true;
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    private static boolean removeTodoList(Integer number) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        for (int i = number; i < todos.length; i++) {
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    public static String input(String info){
        System.out.println(info + " : ");
        return scanner.nextLine();
    }

    public static void showMenuAddToDoList(){
        System.out.println("MENAMBAH TODO LIST");
        String todo = input("Todo (x jika batal)");
        if (todo.equals("x")){
        }else {
            addTodoList(todo);
        }
    }

    public static void showMenuAddRemoveToDoList(){
        System.out.println("MENGHAPUS TODO LIST");
        String todoYangDipilih = input("Nomor todo yang dihapus (x jika batal)");
        if (todoYangDipilih.equals("x")){
        }else {
            boolean success = removeTodoList(Integer.valueOf(todoYangDipilih));
            if (!success){
                System.out.println("Gagal menghapus todo list : " + todoYangDipilih);
            }
        }
    }

    public static void showMenuEditToDoList(){
        System.out.println("MENGEDIT TODO LIST");
        String selectedTodo = input("Masukan nomor todo (x jika batal)");
        if (selectedTodo.equals("x")){
            return;
        }
        String newTodo = input("Masukan todo yang baru (x jika batal)");
        if (newTodo.equals("x")){
            return;
        }
        boolean isEditTodoSuccess = editTodoList(Integer.valueOf(selectedTodo), newTodo);
        if (isEditTodoSuccess){
            System.out.println("Berhasil mengedit todo");
        }else{
            System.out.println("Gagal mengedit todo");
        }
    }
    private static boolean isSelectedTodoNotValid(final Integer number) {
        if (number <= 0) {
            return true;
        }

        if (number - 1 > todos.length - 1) {
            return true;
        }
        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editTodoList(Integer number, String newTodo) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTodoList();
            System.out.println("Menu : ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("Masukan menu yang ingin di pilih");
            switch (selectedMenu) {
                case "1" :
                    showMenuAddToDoList();
                    System.out.println("Menu Add To-Do List");
                    break;
                case "2" :
                    showMenuAddRemoveToDoList();
                    System.out.println("Menu Remove To-Do List");
                    break;
                case "3" :
                    showMenuEditToDoList();
                    System.out.println("Menu Edit To-Do List");
                    break;
                case "4" :
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu dengan Benar!");
            }
        }
    }

}
