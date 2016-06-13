package io.github.didriklind.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="Authors")
public class Author implements Serializable, Comparable<Author> {

	private static final long serialVersionUID = 1L;

	public Author() {
		this.timestamp = Calendar.getInstance();
	}
	
	public Author(String firstName, String lastName, String nickName, int birthYear, String gender, String country,
			String phone, String favouriteBeverage, String favouriteQuote, String roleModel, String username,
			String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.birthYear = birthYear;
		this.gender = gender;
		this.country = country;
		this.phone = phone;
		this.favouriteBeverage = favouriteBeverage;
		this.favouriteQuote = favouriteQuote;
		this.roleModel = roleModel;
		this.username = username;
		this.password = password;
		this.email = email;
		this.timestamp = Calendar.getInstance();
	}

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="FIRST_NAME")
	private String firstName; 
	
	@Column(name="LAST_NAME")
	private String lastName; 
	
	@Column(name="NICK_NAME")
	private String nickName; 
	
	@Column(name="BIRTH_YEAR")
	private int birthYear;

	private String gender;
	
	private String country;
	
	private String phone;
	
	@Column(name="FAVOURITE_BEVERAGE")
	private String favouriteBeverage;
	
	@Column(name="FAVOURITE_QUOTE")
	private String favouriteQuote;
	
	@Column(name="ROLE_MODEL")
	private String roleModel;
	
	private String username;
		
	private String password;
		
	private String email;
	
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timestamp;
	
	
	@OneToMany(mappedBy="author", cascade = CascadeType.ALL, orphanRemoval = true) //cascade = CascadeType.ALL,
	private List<Post> posts = new ArrayList<>();
	
	
	/*Whenever a bidirectional association is formed,
	 *  the application developer must make sure both 
	 *  sides are in-sync at all times. 
	 *  The addPost() and removePost() are utilities methods 
	 *  that synchronize both ends whenever a child element is added or removed.
	 */
	public void addPost(Post post) {
		posts.add(post);
		post.setAuthor(this);
	}
	public void removePost(Post post) {
		posts.remove(post);
		post.setAuthor(null);
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFavouriteBeverage() {
		return favouriteBeverage;
	}

	public void setFavouriteBeverage(String favouriteBeverage) {
		this.favouriteBeverage = favouriteBeverage;
	}

	public String getFavouriteQuote() {
		return favouriteQuote;
	}

	public void setFavouriteQuote(String favouriteQuote) {
		this.favouriteQuote = favouriteQuote;
	}

	public String getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(String roleModel) {
		this.roleModel = roleModel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				firstName,
				lastName,
				nickName,
				birthYear,
				gender,
				country,
				phone,
				favouriteBeverage,
				favouriteQuote,
				roleModel,
				username,
				password,
				email,
				timestamp
		);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (birthYear != other.birthYear)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (favouriteBeverage == null) {
			if (other.favouriteBeverage != null)
				return false;
		} else if (!favouriteBeverage.equals(other.favouriteBeverage))
			return false;
		if (favouriteQuote == null) {
			if (other.favouriteQuote != null)
				return false;
		} else if (!favouriteQuote.equals(other.favouriteQuote))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		if (roleModel == null) {
			if (other.roleModel != null)
				return false;
		} else if (!roleModel.equals(other.roleModel))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nickName=" + nickName
				+ ", birthYear=" + birthYear + ", gender=" + gender + ", country=" + country + ", phone=" + phone
				+ ", favouriteBeverage=" + favouriteBeverage + ", favouriteQuote=" + favouriteQuote + ", roleModel="
				+ roleModel + ", username=" + username + ", password=" + password + ", email=" + email + ", timestamp="
				+ timestamp + ", posts=" + posts + "]";
	}
	
	//0 = equality, 
	@Override
	public int compareTo(Author aAuthor) {
	    if (getTimestamp() == null || aAuthor.getTimestamp() == null)
	        throw new NullPointerException();
	    return getTimestamp().compareTo(aAuthor.getTimestamp());
	}	
	
	//to get simpleformat date. It was a hell in JSF otherwise :p
	public String getSimpleFormatDateStr() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(timestamp.getTime());
	}
	
	
	
}
