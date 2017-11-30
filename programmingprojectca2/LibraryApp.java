/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA2Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cormac
 */
public class LibraryApp
{

    private String name;
    private ArrayList<Items> itemList;
    private String address;
    private long phoneNum;

    public LibraryApp()
    {
        this.name = "";
        this.address = "";
        this.phoneNum = 0;
        this.itemList = new ArrayList<Items>();
    }

    public LibraryApp(String name, String address, int phoneNum)
    {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.itemList = new ArrayList<Items>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Items> getItems()
    {
        return itemList;
    }

    public void setItems(ArrayList<Items> items)
    {
        this.itemList = items;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public long getPhoneNum()
    {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    /**
     *
     * @param i
     * @return
     */
    public boolean add(Items i)
    {
        if (i == null)
        {
            return false;
        }

        if (!this.itemList.contains(i))
        {

            if (i instanceof Film)
            {
                this.itemList.add(new Film((Film) i));
            }
            if (i instanceof Book)
            {
                this.itemList.add(new Book((Book) i));
            }
//            this.itemList.add(new Items(i));
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public int size()
    {
        if (this.itemList != null)
        {
            return this.itemList.size();
        } else
        {
            return 0;
        }
    }

    /**
     *
     * @param index
     * @return
     */
    public Items searchByIndex(int index)
    {
        if (this.itemList.isEmpty())
        {
            return null;
        }

        if ((index >= 0) && (index < this.itemList.size()))
        {

            return new Items(this.itemList.get(index));
        } else
        {
            return null;
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Items searchByName(String name)
    {
        if (this.itemList.isEmpty())
        {
            return null;
        }

        if ((name == null) || (name.length() == 0))
        {
            return null;
        }

        for (Items i : itemList)
        {
            if (this.name.equalsIgnoreCase(name))
            {
                return new Items(i);
            }
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean removeByName(String name)
    {
        Items i = searchByName(name);

        if (i != null)
        {
            this.itemList.remove(i);
            return true;
        }

        return false;
    }

    /**
     *
     * @param index
     * @return
     */
    public boolean removeByIndex(int index)
    {
        if (this.itemList.isEmpty())
        {
            return false;
        }

        if (index >= 0 && index < this.itemList.size())
        {
            this.itemList.remove(index);
            return true;
        } else
        {
            return false;
        }
    }

    /**
     *
     */
    public void clear()
    {
        if (this.itemList != null)
        {
            this.itemList.clear();
        }
    }

    /**
     *
     */
//    public void print()
//    {
//        int index = 0;
//        for (Items i : this.itemList) {
//            System.out.println("[" + index + "]");
//            index++;
//
//            System.out.println(i+"\n");
//        }
//    }
    /**
     *
     * @return
     */
    public List<Items> getAllPersons()
    {
        return Collections.unmodifiableList(itemList);
    }

    @Override
    public String toString()
    {
        return getClass().getName() + "name=" + name + ", itemList=" + itemList + ", address=" + address + ", phoneNum=" + phoneNum + '}';
    }

    public void updateList(Book book, int index)
    {
        //use index to set item in index loc
        Book newBook = new Book(book);
        itemList.remove(index);
        itemList.add(index, newBook);
        System.out.println("Book details have been updated!");
    }

    public void updateList(Film film, int index)
    {
        Film newFilm = new Film(film);
        itemList.remove(index);
        itemList.add(index, newFilm);
        System.out.println("Film details have been updated!");
    }

}
