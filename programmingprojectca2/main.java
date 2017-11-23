/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA2Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author corma
 */
public class main
{

    private LibraryApp library;

    private final String[] mainMenuStringArray =
    {
        "Display", "Admin", "Search", "Sort"
    };

    private final String[] DisplayMenuStringArray =
    {
        "Display All", "Display Books", "Display Films", "Display Library Info", "Go to Main Menu"
    };

    private final String[] AdminMenuStringArray =
    {
        "Add", "Remove", "Edit", "Go to Main Menu"
    };

    private final String[] SearchMenuStringArray =
    {
        "Select Property", "Search By", "Go to Main Menu"
    };

    private final String[] SortMenuStringArray =
    {
        "Select Property", "Search By", "Go to Main Menu"
    };

    public static void main(String[] args)
    {
        main theApp = new main();
        theApp.start();
    }

    private void start()
    {
        initialiseStores();
        showMainMenu();
    }

    private void initialiseStores()
    {
        ArrayList<Items> list = new ArrayList<>();
        this.library = new LibraryApp("DKIT", "Dundalk", 0412563124);
        readDataIn(library);
        
    }

    private void showMainMenu()
    {
        int choice;
        do
        {
            System.out.println("\n************************** Library Management System **************************");
            choice = showMenuGetChoice(mainMenuStringArray);
            switch (choice)
            {
                case 1:
                    // call loadData() method
                    showDisplayMenu("Display Menu", DisplayMenuStringArray);
                    break;
                case 2:
                    showAdminMenu("Admin Menu", AdminMenuStringArray);
                    break;
                case 3:
                    showSearchMenu("Search Menu", SearchMenuStringArray);
                    break;
                case 4:
                    showSorthMenu("Sort Menu", SortMenuStringArray);

                default:
                    break;
            }
        } while (choice != mainMenuStringArray.length); //user enters choice in range 1 - 4 i.e. not zero based

        System.out.println("\nGoodbye...");
    }

    public void showDisplayMenu(String menuHeader, String[] displayMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(displayMenuStringArray);
            //we need to look at the taskMenuStringArray to see what these values are
            if (choice == 1)
            {
                printAllItems();
            } else if (choice == 2)
            {

            } else if (choice == 3)
            {

            } else if (choice == 4)
            {

            }
        } while (choice != displayMenuStringArray.length);
    }

    private void showAdminMenu(String menuHeader, String[] adminMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(adminMenuStringArray);
            //we need to look at the taskMenuStringArray to see what these values are
            if (choice == 1)
            {
                addItems();
            } else if (choice == 2)
            {
                editPerson();
            } else if (choice == 3)
            {
                deleteItems();
            } else if (choice == 4)
            {
                printAllItems();
            }
        } while (choice != adminMenuStringArray.length); //user enters choice in range 1 - 5 i.e. not zero based	
    }

    private void showSearchMenu(String menuHeader, String[] searchMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(searchMenuStringArray);
            //we need to look at the taskMenuStringArray to see what these values are
            if (choice == 1)
            {

            } else if (choice == 2)
            {

            } else if (choice == 3)
            {
            }
        } while (choice != searchMenuStringArray.length); //user enters choice in range 1 - 5 i.e. not zero based	
    }

    private void showSorthMenu(String menuHeader, String[] sortMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(sortMenuStringArray);
            //we need to look at the taskMenuStringArray to see what these values are
            if (choice == 1)
            {

            } else if (choice == 2)
            {

            }
        } while (choice != sortMenuStringArray.length); //user enters choice in range 1 - 5 i.e. not zero based	
    }

    private int showMenuGetChoice(String[] menuOptions)
    {
        int index = 1;
        for (String menuOption : menuOptions)
        {
            System.out.println("[" + index + "]" + menuOption);
            index++;
        }

        //get a valid menu choice - obviously this is directly dependent on the number of strings in the array
        int choice = ScannerUtility.getInt("Enter choice: ", 1, menuOptions.length);

        return choice;
    }

    private void addItems()
    {
        System.out.println();
        boolean bAdded;         // Added flag (type boolean)
        do
        {
            System.out.println("Please enter Person Details");
            String type = ScannerUtility.getString("Enter type('book' or 'film')");
            String title = ScannerUtility.getString("Enter Title: ");
            String genre = ScannerUtility.getString("Enter Genre: ");
            String description = ScannerUtility.getString("Enter description: ");
            double price = ScannerUtility.getDouble("Enter Price: ");
            int releaseYear = ScannerUtility.getInt("Enter Release Year: ");

            bAdded = this.library.add(new Items(type, title, genre, description, price, releaseYear));

            if (bAdded)
            {
                System.out.println("Add completed...");
            } else
            {
                System.out.println("Item alrady exists in the list. Try again.\n");
            }

        } while (!bAdded);
    }

    private void editPerson()
    {
        System.out.println();
        this.library.print();
        int choice = ScannerUtility.getInt("Enter index of items to edit:", 0, this.library.size() - 1);

        Items i = this.library.searchByIndex(choice);

        String newTitle = ScannerUtility.getString("Enter new title (or leave blank to skip): ");
        String newGenre = ScannerUtility.getString("Enter new genre (or leave blank to skip): ");
        String newDesc = ScannerUtility.getString("Enter new description (or leave blank to skip): ");
        double newPrice = ScannerUtility.getDouble("Enter new price (or leave blank to skip): ");
        int newReleaseYear = ScannerUtility.getInt("Enter New Release Year (or leave blank to skip): ");

        //if blank is entered that means user is skipping which means we use existing name/email/tel
        newTitle = (newTitle.length() == 0) ? i.getTitle() : newTitle;
        newGenre = (newGenre.length() == 0) ? i.getGenre() : newGenre;
        newDesc = (newDesc.length() == 0) ? i.getDescription() : newDesc;
        newPrice = (newPrice == 0) ? i.getPrice() : newPrice;
        newReleaseYear = (newReleaseYear == 0) ? i.getReleaseYear() : newReleaseYear;

        i.setTitle(newTitle);
        i.setGenre(newGenre);
        i.setDescription(newDesc);
        i.setPrice(newPrice);
        i.setReleaseYear(newReleaseYear);

        System.out.println("Edit completed...");
    }

    private void deleteItems()
    {
        System.out.println();
        this.library.print();
        int index = ScannerUtility.getInt("Enter index of person to delete(-1 to skip):", -1, this.library.size() - 1);

        if (index != -1)
        {
            boolean bSuccess = this.library.removeByIndex(index);

            if (bSuccess)
            {
                System.out.println("Edit completed...");
            } else
            {
                System.out.println("Edit failed...");
            }
        }
    }

    private void printAllItems()
    {
        System.out.println();
        this.library.print();
    }

    public void readDataIn(LibraryApp library)
    {
        try (BufferedReader br = new BufferedReader(new FileReader("library.csv")))
        {

            String line;   // line of text from file

            while ((line = br.readLine()) != null)
            {

//                System.out.println("*** line = " + line + " ***");
                // create tokenizer and set the delimeter character
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                String type = stringTokenizer.nextElement().toString();
                String title = stringTokenizer.nextElement().toString();
                String genere = stringTokenizer.nextElement().toString();
                String description = stringTokenizer.nextElement().toString();
                Double price = Double.parseDouble(stringTokenizer.nextElement().toString());
                Integer releaseYear = Integer.parseInt(stringTokenizer.nextElement().toString());
                //check to see if its type atches book
                if (type.toLowerCase().equals("book"))
                {
                    //gets relevant book info
                    String isbn = stringTokenizer.nextElement().toString();
                    String author = stringTokenizer.nextElement().toString();
                    Integer pageCount = Integer.parseInt(stringTokenizer.nextElement().toString());
                    Double edidtion = Double.parseDouble(stringTokenizer.nextElement().toString());
                    //adds book item to library arraylist
                    library.add(new Book(type, title, genere, description, price, releaseYear, isbn, author, pageCount, edidtion));

                } else
                {
                    //gets relevant film info
                    String director = stringTokenizer.nextElement().toString();
                    Integer rating = Integer.parseInt(stringTokenizer.nextElement().toString());
                    Integer length = Integer.parseInt(stringTokenizer.nextElement().toString());
                    String studio = stringTokenizer.nextElement().toString();
                    //adds film item to library arrayList
                    library.add(new Film(type, title, genere, description, price, releaseYear, director, rating, length, studio));

                }
                //type check book or film here
                //then goes on t take in relevant data
//                String name = stringTokenizer.nextElement().toString();
                //or can use stringTokenizer.nextToken - this returns a string 
                //nextElement returns an object
//                Integer age = Integer.parseInt(stringTokenizer.nextElement().toString());

//                Double mark = Double.parseDouble(stringTokenizer.nextElement().toString());

//                students.add(new Student(name,age,mark));
                //if(item.instanceof book)
                //write out("book",id, etc);
                //else
                //write out ("film", id, atist);
                //in file - book,id,etc...
                //  film,id,artist...
//                System.out.println("Name: " + name
//                        + "\nAge: " + age
//                        + "\nMark: " + mark);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    //display all elements of an array of type book
    public static void displayBooks(ArrayList<Book> list)
    {
        for (Book books : list)
        {
            System.out.println(books);
        }
    }
    //display all elements fo an array of type film
    public static void displayFilms(ArrayList<Film> list)
    {
        for(Film films : list)
        {
            System.out.println(films);
        }
    }
    /**
     * takes in an arraylist of type item and returns an arraylist of all the book type in it
     * @param list
     * @return 
     */
    public static ArrayList<Book> filterForBooks(ArrayList<Items> list)
    {
        ArrayList<Book> books = new ArrayList<>();
        for (Items item : list)
        {
            //code to check for type pf books
            if(item.getType().toLowerCase().equals("book"))
            {
                Book newBook = (Book) item;
                books.add(newBook);
            }
        }
        return books;
    }
    /**
     * takes in an array list of type item and returns a array list with all the film type objects in it
     * @param list
     * @return 
     */
    public static ArrayList<Film> filterForFilms(ArrayList<Items> list)
    {
        ArrayList<Film> films = new ArrayList<>();
        for(Items item : list)
        {
            //check if type of ite matches "film"
            if(item.getType().toLowerCase().equals("film"))
            {
//                System.out.println("item type: " + item.getType());
//                System.out.println("item name: " + item.getTitle());
                //if it does convert it to a film object so it can be added to new film arraylist
                Film newFilm = (Film) item;
                films.add(newFilm);
            }
        }
        return films;
    }

}
