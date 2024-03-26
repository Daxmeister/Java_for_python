package labs;

public class RekordData implements Comparable<RekordData> {
	private String country;
	private String veggie;
	private int size;
	private String unit;

	public RekordData(String veggieIn, String countryIn, int sizeIn, String unitIn) {
		veggie = veggieIn;
		country = countryIn;
		size = sizeIn;
		unit = unitIn;
	}
	
	public boolean isSame(RekordData other) {
		if (other.country.equals(this.country) && other.veggie.equals( this.veggie)) {return true;}
		else {return false;}
	}

	public int compareTo(RekordData o) {
		// Used for sorting of data entries before printing. Veggie -> size -> country
		
		if (this.veggie.compareTo(o.veggie) > 0) {
			return 1;
		}
		else if (this.veggie.compareTo(o.veggie) < 0) {
			return -1;
		}
		
		
		else if (this.veggie.compareTo(o.veggie) == 0) {
			
			// If they are the same size, sort by country
			if (this.size-o.size == 0) {
			
				if (this.country.compareTo(o.country) > 0) {
					return 1;
				}
				else if (this.country.compareTo(o.country) < 0) {
					return -1;
				}
			}
			else {
				// If not same size, return largest first
				return o.size - this.size;
			}
		}
		
	
		//System.out.println("No condition met, ergo exakt same");
		return 0;
	
	}

	public String getPrintable() {
		return (veggie + " " + country + " " + size + " " + unit);
	}

}
