/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA2Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
        "Display", "Admin", "Search", "Sort", "Save Data", "Exit"
    };

    private final String[] DisplayMenuStringArray =
    {
        "Display All", "Display Books", "Display Films", "Display Library Info", "Go to Main Menu"
    };

    private final String[] AdminMenuStringArray =
    {
        "Add", "Edit", "Remove", "Go to Main Menu"
    };

    private final String[] SearchMenuStringArray =
    {
        "Select Property", "Search By", "Go to Main Menu"
    };

    private final String[] SortMenuStringArray =
    {
        "Sort All", "Sort Books", "Sort Films", "Go to Main Menu"
    };

    private final String[] SortMenuStringArrayAll =
    {
        "Sort by Title", "Sort by Price", "Sort by Genere", "Sort by Release Year", "Go to Sort Menu"
    };

    private final String[] SortMenuStringArrayBooks =
    {
        "Sort by Author", "Sort by Page count", "Sort by title", "sort by price", "Go to Sort Menu"
    };

    private final String[] SortMenuStringArrayFilms =
    {
        "Sort by Rating", "Sort by film length", "Sort by title", "sort by price", "Go to Sort Menu"
    };

    private final String[] SelectItemTypeStringArray =
    {
        "Book", "Film"
    };
    
    private final String[] SaveDataMenu = 
    {
      "yes", "no"  
    };

    public static void main(String[] args)throws FileNotFoundException
    {
        main theApp = new main();
        theApp.start();
    }

    private void start()throws FileNotFoundException
    {
        initialiseStores();
        showMainMenu();
    }

    private void initialiseStores()
    {
//        ArrayList<Items> list = new ArrayList<>();
        this.library = new LibraryApp("DKIT", "Dundalk", 0412563124);
        readDataIn();

    }

    private void showMainMenu()throws FileNotFoundException
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
                    //cormac
                    showDisplayMenu("Display Menu", DisplayMenuStringArray);
                    break;
                case 2:
                    //patrick
                    showAdminMenu("Admin Menu", AdminMenuStringArray);
                    break;
                case 3:
                    //cormac
                    showSearchMenu("Search Menu", SearchMenuStringArray);
                    break;
                case 4:
                    //patrick
                    showSortMenu("Sort Menu", SortMenuStringArray);
                    break;
                case 5:
                   writeData();
                   break;
                default:
                    break;
            }
        } while (choice != mainMenuStringArray.length); //user enters choice in range 1 - 4 i.e. not zero based
        
        System.out.println("Would you like to save your work? ");
        choice = showMenuGetChoice(SaveDataMenu);
        if(choice == 1)
        {
            writeData();
        }
        else{
            System.out.println("Thanks for using our library system.");
            micDrop();
        }
        
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
            //"Display All", "Display Books", "Display Films", "Display Library Info", "Go to Main Menu"
            if (choice == 1)
            {
                printAllItems();
            } else if (choice == 2)
            {
                //"Display Books"
//                displayBooksArray(this.library.getItems());
//                filterForBooks(this.library.getItems());
                printBooks(filterBooks(this.library.getItems()));
            } else if (choice == 3)
            {
                //"Display Films",
//                filterForFilms(this.library.getItems());
                printFilms(filterFilms(this.library.getItems()));
            } else if (choice == 4)
            {
                //"Display Library Info"
                System.out.println("Library Info");
            }
        } while (choice != displayMenuStringArray.length);
    }

    private void showAdminMenu(String menuHeader, String[] adminMenuStringArray)
    {
        int choice;
        OUTER:
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(adminMenuStringArray);
            //we need to look at the taskMenuStringArray to see what these values are
            switch (choice)
            {
                case 1:
                    addItems();
                    break;
                case 2:
                    editItem();
                    break;
                case 3:
                    deleteItems();
                    break;

                default:
                    break;
            }
        } while (choice != adminMenuStringArray.length);
    }

    private void showSearchMenu(String menuHeader, String[] searchMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(searchMenuStringArray);
            //we need to look at the taskMenuStringArray to see what these values are
            //"Select Property", "Search By", "Go to Main Menu"
            if (choice == 1)
            {
//Select Property",
            } else if (choice == 2)
            {
//Search By",
            }
        } while (choice != searchMenuStringArray.length); //user enters choice in range 1 - 5 i.e. not zero based	
    }

    private void showSortMenu(String menuHeader, String[] sortMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(sortMenuStringArray);
            //we need to look at the taskMenuStringArray to see what these values are
            //"Sort All", "Sort Books", "Sort Films", "Go to Main Menu"
            if (choice == 1)
            {
                showSortAllMenu("Sort All Menu", SortMenuStringArrayAll);
            } else if (choice == 2)
            {
                showSortBookMenu("Sort Books Menu", SortMenuStringArrayBooks);//sort by
            } else if (choice == 3)
            {
                showSortFilmMenu("Sort Films Menu", SortMenuStringArrayFilms);
            }
        } while (choice != sortMenuStringArray.length); //user enters choice in range 1 - 5 i.e. not zero based	
    }

    private void showSortAllMenu(String menuHeader, String[] sortMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(sortMenuStringArray);
            if (choice >= 1 || choice < sortMenuStringArray.length)
            {
                sortItemsAll(choice);
            }
        } while (choice != sortMenuStringArray.length); //user enters choice in range 1 - 5 i.e. not zero based	
    }

    private void showSortBookMenu(String menuHeader, String[] sortMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(sortMenuStringArray);
            if (choice >= 1 || choice < sortMenuStringArray.length)
            {
//                System.out.println("sorting by author");
                sortItemsBooks(choice);
            }
        } while (choice != sortMenuStringArray.length); //user enters choice in range 1 - 5 i.e. not zero based	
    }

    private void showSortFilmMenu(String menuHeader, String[] sortMenuStringArray)
    {
        int choice;
        do
        {
            System.out.println("\n\n***************** " + menuHeader + " *****************");
            choice = showMenuGetChoice(sortMenuStringArray);
            if (choice >= 1 || choice < sortMenuStringArray.length)
            {
//                System.out.println(" sort by rating");
                sortItemsFilms(choice);
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
            int choice;
            System.out.println("Please enter new Item Details");
            do
            {
                choice = showMenuGetChoice(SelectItemTypeStringArray);
            } while (choice < 1 || choice > 2);
            if (choice == 1)
            {
                System.out.println("\nEnter Book Details Below:\n-----------------------------\n");
            } else
            {
                System.out.println("\nEnter Film Details Below\n-----------------------------\n");
            }

            String title = ScannerUtility.getString("Enter Title: ");
            String genre = ScannerUtility.getString("Enter Genre: ");
            String description = ScannerUtility.getString("Enter description: ");
            double price = ScannerUtility.getDouble("Enter Price: ");
            int releaseYear = ScannerUtility.getInt("Enter Release Year: ");

            if (choice == 1)
            {
                String isbn = ScannerUtility.getString("enter ISBN: ");
                String author = ScannerUtility.getString("Enter Author: ");
                int pageCount = ScannerUtility.getInt("Enter page count: ");
                double edition = ScannerUtility.getDouble("Enter edition: ");
                bAdded = this.library.add(new Book("Book", title, genre, description, price, releaseYear, isbn, author, pageCount, edition));
            } else
            {
                String director = ScannerUtility.getString("enter Director: ");
                int rating = ScannerUtility.getInt("Enter age rating: ");
                int length = ScannerUtility.getInt("Enter length in minutes: ");
                String studio = ScannerUtility.getString("enter studio: ");
                bAdded = this.library.add(new Film("Film", title, genre, description, price, releaseYear, director, rating, length, studio));
            }

//            bAdded = this.library.add(new Items("book", title, genre, description, price, releaseYear));
            if (bAdded)
            {
                System.out.println("Add completed...");
            } else
            {
                System.out.println("Item alrady exists in the list. Try again.\n");
            }

        } while (!bAdded);
    }

    private void editItem()
    {
        System.out.println();
        printAllItems();
        int choice = ScannerUtility.getInt("Enter index of items to edit:", 0, this.library.size() - 1);

        System.out.println("\nItem Details :");
        System.out.println(this.library.getItems().get(choice));

        String newTitle = ScannerUtility.getString("Enter new title (or leave blank to skip): ");
        String newGenre = ScannerUtility.getString("Enter new genre (or leave blank to skip): ");
        String newDesc = ScannerUtility.getString("Enter new description (or leave blank to skip): ");
        double newPrice = ScannerUtility.getDouble("Enter new price (or '0' to skip): ");
        int newReleaseYear = ScannerUtility.getInt("Enter New Release Year (or '0' to skip): ");

        if (this.library.getItems().get(choice) instanceof Book)
        {
//            System.out.println(" its a boook!!!");

            Book editBook = new Book((Book) this.library.getItems().get(choice));
            String newIsbn = ScannerUtility.getString("Enter new ISBN (or leave blank to skip): ");
            String newAuthor = ScannerUtility.getString("Enter new author (or leave blank to skip): ");
            int newPageCount = ScannerUtility.getInt("Enter new page count (or '0' to skip): ");
            double newEdition = ScannerUtility.getDouble("Enter new edition (or '0' to skip): ");

            //if blank is entered that means user is skipping which means we use existing name/email/tel
            newTitle = (newTitle.length() == 0) ? editBook.getTitle() : newTitle;
            newGenre = (newGenre.length() == 0) ? editBook.getGenre() : newGenre;
            newDesc = (newDesc.length() == 0) ? editBook.getDescription() : newDesc;
            newPrice = (newPrice == 0) ? editBook.getPrice() : newPrice;
            newReleaseYear = (newReleaseYear == 0) ? editBook.getReleaseYear() : newReleaseYear;
            newIsbn = (newIsbn.length() == 0) ? editBook.getIsbn() : newIsbn;
            newAuthor = (newAuthor.length() == 0) ? editBook.getAuthor() : newAuthor;
            newPageCount = (newPageCount == 0) ? editBook.getPageCount() : newPageCount;
            newEdition = (newEdition == 0) ? editBook.getEdition() : newEdition;

            editBook.setTitle(newTitle);
            editBook.setGenre(newGenre);
            editBook.setDescription(newDesc);
            editBook.setPrice(newPrice);
            editBook.setReleaseYear(newReleaseYear);
            editBook.setIsbn(newIsbn);
            editBook.setAuthor(newAuthor);
            editBook.setPageCount(newPageCount);
            editBook.setEdition(newEdition);

//            System.out.println("original book: " + this.library.getItems().get(choice));
//            System.out.println(" edit book: " + editBook);
            //update arraylist
            this.library.updateList(editBook, choice);

        } else if (this.library.getItems().get(choice) instanceof Film)
        {
//            System.out.println("its a film!!!");
            Film editFilm = new Film((Film) this.library.getItems().get(choice));
            String newDirector = ScannerUtility.getString("Enter new director (or leave blank to skip): ");
            int newRating = ScannerUtility.getInt("Enter new age rating (or '0' to skip): ");
            int newLength = ScannerUtility.getInt("Enter new length (in minutes) (or '0' to skip): ");
            String newStudio = ScannerUtility.getString("Enter new studio (or leave blank to skip): ");

            //if blank is entered that means user is skipping which means we use existing name/email/tel
            newTitle = (newTitle.length() == 0) ? editFilm.getTitle() : newTitle;
            newGenre = (newGenre.length() == 0) ? editFilm.getGenre() : newGenre;
            newDesc = (newDesc.length() == 0) ? editFilm.getDescription() : newDesc;
            newPrice = (newPrice == 0) ? editFilm.getPrice() : newPrice;
            newReleaseYear = (newReleaseYear == 0) ? editFilm.getReleaseYear() : newReleaseYear;
            newDirector = (newDirector.length() == 0) ? editFilm.getDirector() : newDirector;
            newRating = (newRating == 0) ? editFilm.getLength() : newRating;
            newLength = (newLength == 0) ? editFilm.getLength() : newLength;
            newStudio = (newStudio.length() == 0) ? editFilm.getStudio() : newStudio;

            editFilm.setTitle(newTitle);
            editFilm.setGenre(newGenre);
            editFilm.setDescription(newDesc);
            editFilm.setPrice(newPrice);
            editFilm.setReleaseYear(newReleaseYear);
            editFilm.setDirector(newDirector);
            editFilm.setRating(newRating);
            editFilm.setLength(newLength);
            editFilm.setStudio(newStudio);

            this.library.updateList(editFilm, choice);
        } else
        {
            System.out.println(" sorry there was an error");
        }
        System.out.println("Edit completed...");
    }

    private void deleteItems()
    {
        System.out.println();
//        this.library.print();
        printAllItems();
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
        System.out.println("Printing all the items");
        System.out.println();
//        this.library.print();
        int index = 0;
        for (Items i : this.library.getItems())
        {
            System.out.print("index: [" + index + "]");
            index++;

            System.out.println(" " + i + "\n");
        }
    }


    //display all elements fo an array of type film
    public static void displayFilmsArray(ArrayList<Film> list)
    {
        for (Film films : list)
        {
            System.out.println(films);
        }
    }

    public static void filterForBooks(ArrayList<Items> list)
    {
        System.out.println("filter for books");

        for (Items item : list)
        {
            if (item instanceof Book)
            {
                System.out.println(item);
            }

//            if (item.getType().toLowerCase().equals("book"))
//            {
//                System.out.println(item);
//
//            }
        }
    }

    /**
     * takes in an array list of type item and returns a array list with all the
     * film type objects in it
     *
     * @param list
     * @return
     */
    public static void filterForFilms(ArrayList<Items> list)
    {
        for (Items item : list)
        {
            //check if type of ite matches "film"
            if (item instanceof Film)
            {
                System.out.println(item);
            }
        }
    }

    /**
     * takes in an arraylist of type item and returns an arraylist of all the
     * book type in it
     *
     * @param list
     * @return
     */
    public static ArrayList<Book> filterBooks(ArrayList<Items> list)
    {
        System.out.println("filter for books");
        ArrayList<Book> books = new ArrayList<>();
//        System.out.println("crate array");
        for (Items item : list)
        {
//            System.out.println(item);
//            System.out.println("type: " + item.getType());
            //code to check for type pf books
            if (item instanceof Book)
            {
//                System.out.println(item);
                //makesa  clone and adds it to the srraylist
                Book newBook = new Book((Book) item);
                books.add(newBook);
            }
        }
        return books;
    }

    public void printBooks(ArrayList<Book> books)
    {
        for (Book book : books)
        {
//            System.out.println("\nTitle: " +  book.getTitle() + 
//                                "\nAuthor: " + book.getAuthor() + 
//                                "\nDescription: " + book.getDescription() + 
//                                "\nGenere: " + book.getGenre() + 
//                                "\nPageCount: " + book.getPageCount() + 
//                                "\nEdtion: " + book.getEdition() + 
//                                "\nISBN: " + book.getIsbn() + 
//                                "\nPrice: " + book.getPrice());
//            System.out.println("------------------- other edit");

            System.out.printf("\n\n\033[0;1mTitle: \t\t%-15s \nAuthor: \t%-15s \n\033[0;1mDescription: \t%s \nGenere: \t%-15s \n\033[0;1mPageCount \t%d \nEdtion: \t%.2f \n\033[0;1mIsbn: \t\t%-10s \nPrice: \t\t€%.2f",
                    book.getTitle(), book.getAuthor(), book.getDescription(), book.getGenre(), book.getPageCount(), book.getEdition(), book.getIsbn(), book.getPrice());
        }
    }

    public static ArrayList<Film> filterFilms(ArrayList<Items> list)
    {
        System.out.println("filter for books");
        ArrayList<Film> films = new ArrayList<>();
//        System.out.println("crate array");
        for (Items item : list)
        {
//            System.out.println(item);
//            System.out.println("type: " + item.getType());
            //code to check for type pf books
            if (item instanceof Film)
            {
//                System.out.println(item);
                Film newBook = new Film((Film) item);
                films.add(newBook);
            }
        }
        return films;
    }

    public void printFilms(ArrayList<Film> films)
    {
        for (Film film : films)
        {
//            System.out.println(film);

            System.out.printf("\n\n\033[0;1mTitle: \t\t%-15s \nDirector: \t%-15s \n\033[0;1mDescription: \t%s \nGenere: \t%-15s \n\033[0;1mRating \t\t%d \nLength: \t%d(mins) \n\033[0;1mStudio: \t%-10s \nPrice: \t\t€%.2f",
                    film.getTitle(), film.getDirector(), film.getDescription(), film.getGenre(), film.getRating(), film.getLength(), film.getStudio(), film.getPrice());

        }
    }

    public void printAllTheThings()
    {
        for (int i = 0; i < this.library.getItems().size(); i++)
        {
            System.out.println("\nIndex [" + i + "]");
            System.out.printf("\033[0;1mTitle: \t\t%-15s  \nDescription: \t%s \n\033[0;1mGenere: \t%-15s \nRelease Year: \t%d",
                    this.library.getItems().get(i).getTitle(), this.library.getItems().get(i).getDescription(), this.library.getItems().get(i).getGenre(), this.library.getItems().get(i).getReleaseYear());
            if (this.library.getItems().get(i) instanceof Book)
            {
                Book book = new Book((Book) this.library.getItems().get(i));
                System.out.printf("\n\033[0;1mAuthor: \t%-15s\nPageCount \t%d \n\033[0;1mEdtion: \t%.2f \nIsbn: \t\t%-10s \n\033[0;1mPrice: \t\t€%.2f\n",
                        book.getAuthor(), book.getPageCount(), book.getEdition(), book.getIsbn(), book.getPrice());
            }
            if (this.library.getItems().get(i) instanceof Film)
            {
                Film film = new Film((Film) this.library.getItems().get(i));
                System.out.printf("\n\033[0;1mDirector: \t%-15s \nRating \t\t%d \n\033[0;1mLength: \t%d(mins) \nStudio: \t%-10s \n\033[0;1mPrice: \t\t€%.2f\n",
                        film.getDirector(), film.getRating(), film.getLength(), film.getStudio(), film.getPrice());
            }
        }
    }

    public void sortItemsAll(int choice)
    {
        if (choice == 1)
        {
            System.out.println("\nSort by Title");
            ItemTitleComparator titleComparator = new ItemTitleComparator();
            Collections.sort(this.library.getItems(), titleComparator);
//            Items.DisplayItems(this.library.getItems());
            printAllTheThings();

        } else if (choice == 2)
        {
            System.out.println("\nSorting by Price");
            ItemPriceComparator priceComparator = new ItemPriceComparator();
            Collections.sort(this.library.getItems(), priceComparator);
//            Items.DisplayItems(this.library.getItems());
            printAllTheThings();
        } else if (choice == 3)
        {
            System.out.println("\nSort by Genere");
            ItemGenereComparator genereComparator = new ItemGenereComparator();
            Collections.sort(this.library.getItems(), genereComparator);
            printAllTheThings();
        } else if (choice == 4)
        {
            System.out.println("\nSort by Release year");
            ItemReleaseYearComparator releaseYearComparator = new ItemReleaseYearComparator();
            Collections.sort(this.library.getItems(), releaseYearComparator);
            printAllTheThings();
        }
    }

    public void sortItemsBooks(int choice)
    {
        ArrayList<Book> books = filterBooks(this.library.getItems());

        if (choice == 1)
        {
            System.out.println("\nSort by Author");
            BookAuthorComparator authorComparator = new BookAuthorComparator();
            Collections.sort(books, authorComparator);
            printBooks(books);
        } else if (choice == 2)
        {
            System.out.println("\nSort by Page count");
            BookPageComparator pageComparator = new BookPageComparator();
            Collections.sort(books, pageComparator);
            printBooks(books);
        } else if (choice == 3)
        {
            System.out.println("\nSort by Title");
            ItemTitleComparator titleComparator = new ItemTitleComparator();
            Collections.sort(books, titleComparator);
            printBooks(books);
        } else if (choice == 4)
        {
            System.out.println("\nSorting by Price");
            ItemPriceComparator priceComparator = new ItemPriceComparator();
            Collections.sort(books, priceComparator);
            printBooks(books);
        }
    }

    public void sortItemsFilms(int choice)
    {
        ArrayList<Film> films = filterFilms(this.library.getItems());

        if (choice == 1)
        {
            System.out.println("\nSort by Rating");
            FilmRatingComparator ratingComparator = new FilmRatingComparator();
            Collections.sort(films, ratingComparator);
            printFilms(films);
        } else if (choice == 2)
        {
            System.out.println("\nSort by Length");
            FilmLengthComparator lengthComparator = new FilmLengthComparator();
            Collections.sort(films, lengthComparator);
            printFilms(films);
        } else if (choice == 3)
        {

            System.out.println("\nSort by Title");
            ItemTitleComparator titleComparator = new ItemTitleComparator();
            Collections.sort(films, titleComparator);
            printFilms(films);
        } else if (choice == 4)
        {
            System.out.println("\nSorting by Price");
            ItemPriceComparator priceComparator = new ItemPriceComparator();
            Collections.sort(films, priceComparator);
            printFilms(films);
        }
    }
    
    
    public void readDataIn()
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
                if (type.equalsIgnoreCase("book"))
                {
                    //gets relevant book info
                    String isbn = stringTokenizer.nextElement().toString();
                    String author = stringTokenizer.nextElement().toString();
                    Integer pageCount = Integer.parseInt(stringTokenizer.nextElement().toString());
                    Double edidtion = Double.parseDouble(stringTokenizer.nextElement().toString());
                    //adds book item to library arraylist
                    this.library.add(new Book(type, title, genere, description, price, releaseYear, isbn, author, pageCount, edidtion));

                } else
                {
                    //gets relevant film info
                    String director = stringTokenizer.nextElement().toString();
                    Integer rating = Integer.parseInt(stringTokenizer.nextElement().toString());
                    Integer length = Integer.parseInt(stringTokenizer.nextElement().toString());
                    String studio = stringTokenizer.nextElement().toString();
                    //adds film item to library arrayList
                    this.library.add(new Film(type, title, genere, description, price, releaseYear, director, rating, length, studio));

                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeData() throws FileNotFoundException
    {
        System.out.println("\n---------------------------------------\n\tWriting data to file....\n---------------------------------------\n");
        try (PrintWriter out = new PrintWriter("library.csv"))
        {
            System.out.print("\t");
            for(Items item : this.library.getItems())
            {
                
                out.printf("%s,%s,%s,%s,%.2f,%d,",item.getType(), item.getTitle(),item.getGenre(), item.getDescription(), item.getPrice(), item.getReleaseYear());
                if(item instanceof Book)
                {
                    Book book = new Book((Book) item);
                    out.printf("%s,%s,%d,%.1f\n", book.getIsbn(), book.getAuthor(), book.getPageCount(), book.getEdition());
                    System.out.print(".");
                }
                else
                {
                    Film film = new Film((Film) item);
                    out.printf("%s,%d,%d,%s\n", film.getDirector(), film.getRating(),film.getLength(), film.getStudio());
                    System.out.print(".");
                }
//                out.printf("%s\n", this.library.getItems().get(i).getTitle());
            }
            
            System.out.println("\n\n---------------------------------------\n\t...Writing data complete!\n---------------------------------------\n");
            
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    
    public void micDrop()
    {
        System.out.println("                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                         `.`                                                                          \n" +
"                                                                .,`     .i;i.                                                                         \n" +
"                                                               ,i;i.    ;:,:;                                                                         \n" +
"                                                              `i:,::   `;,.,;                                                                         \n" +
"                                                              `;..,:   ,:..,:   `..                                                                   \n" +
"                                                              .:..,:   :,..:,  `i;i;                                                                  \n" +
"                                                              .:..,:  `;...;.  ::,,i.                                                                 \n" +
"                                                              ,:..,:  .:..,;` `;,.,;.                                                                 \n" +
"                                                              ,,..,,  :,..,;  ,:..,;                                                                  \n" +
"                                                       ,:.    :,..:,  ;,..,:  ;,..,:                                                                  \n" +
"                                                      :;;i.   :,..:, .:...:, .;...:,                                                                  \n" +
"                                                      ;:,::   ;,..:. ,,...;` :,..,;`                                                                  \n" +
"                                                      ::.,;`  ;...:. :,..,; `;...,:                                                                   \n" +
"                                                      .:..:,  ;...;``;,..,: ,:...:,                                                                   \n" +
"                                                      `;..,: `;...;``;...:, ;,...;`                                                                   \n" +
"                                                       :,.,;`.:...; ,:...;..:...,;                                                                    \n" +
"                                                       ,:..:,.:..,; :,..,; :,...:,                                                                    \n" +
"                                                       .;..,;,,...; ;...,:`;....;`                                                                    \n" +
"                                                        ;,..::....::,....::,...,;                                                                     \n" +
"                                                        :,...............,,....,:                                                                     \n" +
"                                                        .:.....................:,                                                                     \n" +
"                                                        `;.....................,,                                                                     \n" +
"                                                         :,....................,:                                                                     \n" +
"                                                         :,.......,......,......;.                                                                    \n" +
"                                                         ,:........,....,,......,;,                                                                   \n" +
"                                                         .:......,,....,,........,:;::;;::,`                                                          \n" +
"                                                         `;......,.....,,..........,,,,,,:;i.                                                         \n" +
"                                                          ;,....,......,...................:;                                                         \n" +
"                                                          ;,...........,...................,i`                                                        \n" +
"                                                          .;,......................,.......,i`                                                        \n" +
"                                                           ,;,.................,:;:;;:,,,,,;:                                                         \n" +
"                                                            .;:,.............,:;,`  `.::;;;,                                                          \n" +
"                                                             `,;;:::::,....,:;,`                                                                      \n" +
"                                                                `,,,.,:;;;;:,`                                                                        \n" +
"                                                                 ` ` `` ``                                                                            \n" +
"                                                                 ` ` ``                                                                               \n" +
"                                                                 ` ` ``                                                                               \n" +
"                                                                 ` ` ``                                                                               \n" +
"                                                                 ` . ``                                                                               \n" +
"                                                                 . . ``                                                                               \n" +
"                                                                 . . ``                                                                               \n" +
"                                                                 . . ``                                                                               \n" +
"                                                                 . .```                                                                               \n" +
"                                                       `:;##nz#+**:;...                                                                               \n" +
"                                                       #xnxnxxxxxxMMMMxnz+*i;:.`     `.:i***+:                                                        \n" +
"                                                      ,xxnxnxxxxxxxxxxxxxxxMMMMMMxn#+zzzzz+MWWz`                                                      \n" +
"                                                      :xxnxnxxxxxxxxxxxxxxxxxxxxxxx#zzzzz#*WWWWn`                                                     \n" +
"                                                      :Mxnxnxxxxxxxxxxxxxxxxxxxxxxx+####z+#WWWWW*                                                     \n" +
"                                                      `nMnMnMMxxxxxxxxxxxxxxxxxxxxn+#####*nWWWWWx                                                     \n" +
"                                                        ,+xxMMMMMMMMMMxxxxxxxxxxxxz######iMWWWWWW`                                                    \n" +
"                                                           :i*#zxMMMMMMMMMMMMMMMxxnzzzzz#iWWWWWWW`                                                    \n" +
"                                                                  .,;i+znxMMMMMMMMnzzzzzz*WWWWWWx                                                     \n" +
"                                                                          `,:i+#nxxznzzzz*WWWWWW*                                                     \n" +
"                                                                                  .:#nnnn+WWWWWx`                                                     \n" +
"                                                                                     ,*zn#MWWWn.                                                      \n" +
"                                                                                        .:+z#:                                                        \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                                                                                                      \n" +
"                                                                    ``````````````````````````````                                                    \n" +
"                                                            `````......,,,,,,,,,..........`````````                                                   \n" +
"                                                           ````.....,,,,,,,,,,,,,,,.........```````                                                   \n" +
"                                                                `````````....`.````````````````````                                                   \n" +
"                                                                                 `````````````````     ");
    }

}
