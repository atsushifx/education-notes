
// extract the unique country names
const rawCountryList = universityJson.map((item) => item.country);
const countryList = rawCountryList.filter(
  (item, index, self) => self.indexOf(item) === index
);
countryList.sort();

//console.log(rawCountryList);
//console.log(countryList);

// components
Vue.component("v-select", VueSelect.VueSelect);

// vue
const app = new Vue ({
  el: "#vue-app",

  data: {
    countries: countryList,
    countrySelected: "",
    universities: [ ],
    universitySelected: null,
  },

  methods: {
    fetchUniversities: function() {
        console.log("fetch universities for " + this.countrySelected);

        let matches = universityJson.filter(item => item.country==this.countrySelected)
        matches.sort((l, r) => (l.name < r.name ) ? -1 : 1);

        this.universities = matches;
      },  
  },

});
