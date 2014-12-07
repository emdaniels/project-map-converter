// Last Modified: 4-15-2014
// Author: James DeMond and Emily Daniels

var website = 'http://www.emilydaniels.com/treecanada';
var tcProgramTxt = ['TC Program', 'Programme d\'AC'];
var sponsorTxt = ['Sponsor', 'Commanditaire'];
var sponsorsTxt = ['Sponsors', 'Commanditaires'];
var plantingsTxt = ['Projects', 'Projets'];
var locationTxt = ['Location', 'Localisation'];
var projectGoalsTxt = ['Project Goal(s)', 'Objectif(s) du projet'];
var regionTxt = ['Region', 'Région'];
var environmentalBenefitsTxt = ['Environmental Benefits', 'Bénéfices environnementaux'];
var environmentalBenefitsPreBlurb = ['', 'Approximativement '];
var environmentalBenefitsBlurb = ['tonnes of CO2 should approximately be sequestered over an 80-year period.', 'tonnes de CO2 devraient être séquestrés dans une période de 80 ans.'];
var treesPlantedTxt = ['Trees Planted', 'Nombre d\'arbres plantés'];
var readMoreTxt = ['Read more...', 'En savoir plus...'];
var additionalNotesTxt = ['Additional Notes', 'Notes supplémentaires'];
var asterixTxt = ['*Data derived from the Tree Canada publication: What Trees Can Do to Reduce Atmospheric CO2.', '*Données dérivées de la publication d’Arbres Canada : Le rôle des arbres dans la réduction du CO2 dans l’atmosphère.'];
var langCode = ['EN', 'FR'];

var map;
var plantingsUrl = website + '/data/plantings_' + langCode[lang] + '.js?jsoncallback=?';
var sponsorsUrl = website + '/data/sponsors_' + langCode[lang] + '.js?jsoncallback=?';
var canada = new google.maps.LatLng(49.815779,-97.233824);
var default_zoom = 3;
var site_zoom = 14;
var infoWindow = new google.maps.InfoWindow({});
var plantingsYears = null; // json planting year object
var sponsorsYears = null; // json sponsor year object
var markersArray = new Array(); // all the markers on the map

// MAP RELATED JAVASCRIPT

// initializes the map, sets things and populates the lists
function initialize() {
	var mapOptions = {
		zoom: default_zoom,
		center: canada,
		mapTypeId: google.maps.MapTypeId.HYBRID
	};
	
	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	
	// default
	hideShowPlantingYears();
	hideShowPlantingList('2013');
}

//DATA LOAD JAVASCRIPT

// common functions

// utility method that clears all the markers on the map
function clearMarkers() {
	
	for (var i = 0; i < markersArray.length; i++) {
		markersArray[i].setMap(null);
	}
	
	markersArray = [];
	
	map.panTo(canada);
	map.setZoom(default_zoom);
	
	$('#planting_data').html("");
}

//creates a marker object on the map
function createMarker(year, program, programLink, programLogoLink, sponsorsArray, location) {
	var image = website + '/images/treeicon30.png';
	var coordinates = location.coordinates.split(",");
	var latLang = new google.maps.LatLng(coordinates[0], coordinates[1]);
	
	var marker = new google.maps.Marker({
		position: latLang,
		title: location.name,
		map: map,
		clickable: true,
		icon: image,
	});
	
	// when an icon on the map is clicked, this stuff happens
	google.maps.event.addListener(marker, 'click', function() {
		var content = "<p><img src='" + website + programLogoLink + "'><br />";
		content += "<strong>" + tcProgramTxt[lang] + "</strong> <a href='" + programLink + "' target='_blank'>" + program + "</a><br />";
		
		content += "<strong>" + sponsorTxt[lang] + "</strong> ";
		
		for (var key in sponsorsArray) {
			var names = sponsorsArray[key].name.split(", ");
			var links = sponsorsArray[key].sponsorLink.split(", ");
			
			for (var key2 in names) {
				
				if (links[key2]) {
					content += "<a href='" + links[key2] + "' target='_blank'>" + names[key2] + "</a>";
				} else {
					content += names[key2];
				}
				
				if (key2 < names.length-1) {
					content += ", ";
				}
			}
		}
		
		content += "<br />"
		content += "<strong>" + locationTxt[lang] + "</strong><br />" + location.name + "<br />" + location.address + "<br />" + location.city + ", " + location.province + "<br />";
		content += "<strong>" + treesPlantedTxt[lang] + "</strong> " + location.totalPlanted + "</p>";
		
		infoWindow.setContent(content);
		infoWindow.open(map, marker);
		
		changeLocation(year, program, programLink, programLogoLink, sponsorsArray, location, name); //e
	});
	
	return marker;
}

//changes the information displayed below the map
function changeLocation(year, program, programLink, programLogoLink, sponsorsArray, location, name) {
	var content = "<div><div style='float:right; height=400px; width=400px'><br /><br />";
	content += "<img src='" + website + location.imageLink + "' height='300' width='400'>";
	content += "</div>";
	content += "<div style='height=300px; width=300px'>";
	content += "<div style='height=50px;'>&nbsp;</div>";
	content += "<img src='" + website + programLogoLink + "'>";
	content += "<h2><a href='" + programLink + "' target='_blank'>" + program + "</a></h2>";
	content += "</div>";
	
	if (location.projectGoals != "") {
		content += "<div style='height=300px; width=300px'>";
		content += "<h4>" + projectGoalsTxt[lang] + "</h4>";
		content += location.projectGoals;
		content += "</div>";
	}
	
	content += "<div style='height=300px; width=700px'>";
	
	if (sponsorsArray.length == 1) {
		content += "<h4>" + sponsorTxt[lang] + "</h4>";
	} else if (sponsorsArray.length > 1) {
		content += "<h4>" + sponsorsTxt[lang] + "</h4>";
	}
	
	for (var key in sponsorsArray) {
		var logos = sponsorsArray[key].sponsorLogoLink.split(", ");
		var links = sponsorsArray[key].sponsorLink.split(", ");
		var names = sponsorsArray[key].name.split(", ");
		
		for (var key2 in logos) {
			if (links[key2]) {
				content += "<p><a href='" + links[key2] + "' target='_blank'><img src='" + website + logos[key2] + "' alt='" + names[key2] + "' title='" + names[key2] + "'></a></p>";
			} else {
				content += "<p>" + names[key2] + "</p>";
			}
		}
		
		
		//content += "<a href='" + sponsorsArray[key].sponsorLink + "'>" + sponsorsArray[key].name + "</a>";
	}
	
	content += "</div>";
	
	content += "<div style='height=300px; width=700px'>";
	content += "<h4>" + regionTxt[lang] + "</h4>";
	content += location.region;
	content += "</div>";
	
	content += "<div style='height=300px; width=700p'>";
	content += "<h4>" + treesPlantedTxt[lang] + "</h4>";
	var trees = location.trees;
	
	for (var key in trees) {
		content += trees[key].numberPlanted + " " + trees[key].treeSpecies + "<br />";
	}
	
	content += "</div>";
	
	content += "<div style='height=300px; width=700px'>";
	content += "<h4>" + environmentalBenefitsTxt[lang] + "</h4>";
	content += environmentalBenefitsPreBlurb[lang] + location.environmentalBenefits + " " + environmentalBenefitsBlurb[lang] + "*";
	content += "</div>";
	
	if (location.additionalNotes != null) {
		content += "<div style='height=300px; width=700px'>";
		content += "<h4>" + additionalNotesTxt[lang] + "</h4>";
		content += location.additionalNotes + "<br />";
		content += "</div>";
	}
	
	content += "<div style='height=300px; width=700px'>";
	content += "<p>" + asterixTxt[lang] + "</p>";
	content += "</div>";
	
	$('#planting_data').html(content);
}

// PLANTING SPECIFIC
// toggles all planting years
function hideShowPlantingYears() {
	$('#sponsors').hide();

	for (var key in plantingsYears) {
		var year = plantingsYears[key].year;
		$('#plantings' + year).hide();
	}
	
	$('#' + key).hide();
	
	$('#plantings').toggle(); 
	$('#title_text').html(plantingsTxt[lang]);
	
	clearMarkers();
}

// toggles all planting items in a specific year
function hideShowPlantingList(year) {
	
	for (var key in plantingsYears) {
		var plantingYear = plantingsYears[key].year;
		
		if (year != plantingYear) {
			$('#plantings' + plantingYear).hide();
		}
	}
	
	$('#plantings' + year).toggle();
	$('#title_text').html(plantingsTxt[lang] + ' - ' + year);
	
	clearMarkers();
	createPlantingMarkersByYear(year);
}

// shows all plantings for a specific year and program
function showPlantingsForProgram(year, key) {
	var plantings = getPlantings(year);
	
	$('#title_text').html(plantingsTxt[lang] + ' - ' + year + ' - ' + plantings[key].program);
	
	clearMarkers();
	createPlantingMarkersByYearAndProgram(year, plantings[key].program);
}

//loops through global variable plantings and calls createMarker method for each item in the array
function createPlantingMarkersByYear(year) {
	var plantings = getPlantings(year);
		
	for (var plantingKey in plantings) {
		var planting = plantings[plantingKey];
		var program = planting.program;
		var programLink = planting.programLink;
		var programLogoLink = planting.programLogoLink;
		var locationsArray = planting.locations;
		
		for (var key in locationsArray) {
			var sponsorsArray = locationsArray[key].sponsors;
			markersArray.push(createMarker(year, program, programLink, programLogoLink, sponsorsArray, locationsArray[key]));
		}
	}
}

//loops through global variable plantings and calls createMarker method for each item in the array
function createPlantingMarkersByYearAndProgram(year, program) {
	var plantings = getPlantings(year);
	
	for (var plantingKey in plantings) {
		var planting = plantings[plantingKey];
		
		if (program == planting.program) {
			var program = planting.program;
			var programLink = planting.programLink;
			var programLogoLink = planting.programLogoLink;
			var locationsArray = planting.locations;
			
			for (var key in locationsArray) {
				var sponsorsArray = locationsArray[key].sponsors;
				markersArray.push(createMarker(year, program, programLink, programLogoLink, sponsorsArray, locationsArray[key]));
			}
		}
	}
}

//generates the left nav list for plantings 
function loadPlantingsList() {
	var outputHtml = '';
	
	for (var key in plantingsYears) {
		var yearHtml = '<li><a href="javascript:void(0);" onClick="javascript:hideShowPlantingList(\'' + plantingsYears[key].year + '\')"><strong>' + plantingsYears[key].year + '</strong></a>';
		yearHtml += '<ul id="plantings' + plantingsYears[key].year + '" class="notvisible">';
		
		var plantings = plantingsYears[key].plantings;
		
		for (var key2 in plantings) {
			yearHtml += '<li><a href="javascript:void(0);" onClick="javascript:showPlantingsForProgram(\'' + plantingsYears[key].year + '\',\'' + key2 + '\')" target="_self">' + plantings[key2].program + '</a></li>';
		}
		
		yearHtml += '</ul>';
		yearHtml += '</li>';
		
		outputHtml = yearHtml + outputHtml;
	}
	
	$('#plantings').html(outputHtml);
}

function getPlantings(year) {
	
	for (var yearKey in plantingsYears) {
		
		if (plantingsYears[yearKey].year == year) {
			return plantingsYears[yearKey].plantings;
		}
	}
}

// SPONSOR SPECIFIC
// show all planting years
function hideShowSponsorYears() {
	$('#plantings').hide();

	for (var key in sponsorsYears) {
		var year = sponsorsYears[key].year;
		$('#sponsors' + year).hide();
	}
	
	$('#' + key).hide();
	
	$('#sponsors').toggle(); 
	$('#title_text').html(sponsorsTxt[lang]);
	
	clearMarkers();
}

// show all items in a specific year for a type
function hideShowSponsorList(year) {
	
	for (var key in sponsorsYears) {
		var sponsorYear = sponsorsYears[key].year;
		
		if (year != sponsorYear) {
			$('#sponsors' + sponsorYear).hide();
		}
	}
	
	$('#sponsors' + year).toggle();
	$('#title_text').html(sponsorsTxt[lang] + ' - ' + year);
	
	clearMarkers();
	createSponsorMarkersByYear(year);
}

//changes the header for sponsors
function showPlantingsForSponsor(year, key) { //e
	var sponsors = getSponsors(year);
	
	$('#title_text').html(sponsorsTxt[lang] + ' - ' + year + ' - ' + sponsors[key].name);//e
	clearMarkers();
	createSponsorMarkersByYearAndName(year, sponsors[key].name);
}

//loops through global variable plantings and calls createMarker method for each item in the array
function createSponsorMarkersByYear(year) {
	var sponsors = getSponsors(year);

	for (var sponsorKey in sponsors) {
		for (var plantingKey in sponsors[sponsorKey].plantings) {
			var planting = sponsors[sponsorKey].plantings[plantingKey];
			var program = planting.program;
			var programLink = planting.programLink;
			var programLogoLink = planting.programLogoLink;
			var locationsArray = planting.locations;
			
			for (var key in locationsArray) {
				var sponsorsArray = locationsArray[key].sponsors;
				markersArray.push(createMarker(year, program, programLink, programLogoLink, sponsorsArray, locationsArray[key]));
			}
		}
	}
}

//loops through global variable plantings and calls createMarker method for each item in the array
function createSponsorMarkersByYearAndName(year, name) {
	var sponsors = getSponsors(year);
	
	for (var sponsorKey in sponsors) {
		var sponsor = sponsors[sponsorKey]; 
		
		if (name == sponsor.name) {
		
			for (var plantingKey in sponsors[sponsorKey].plantings) {
				var planting = sponsors[sponsorKey].plantings[plantingKey];
				var program = planting.program;
				var programLink = planting.programLink;
				var programLogoLink = planting.programLogoLink;
				var locationsArray = planting.locations;
				
				for (var key in locationsArray) {
					var sponsorsArray = locationsArray[key].sponsors;
					markersArray.push(createMarker(year, program, programLink, programLogoLink, sponsorsArray, locationsArray[key]));
				}
			}
		}
	}
}

// generates the left nav list for sponsors
function loadSponsorsList() {
	var outputHtml = '';
	
	for (var key in sponsorsYears) {
		var yearHtml = '<li><a href="javascript:void(0);" onClick="javascript:hideShowSponsorList(\'' + sponsorsYears[key].year + '\')"><strong>' + sponsorsYears[key].year + '</strong></a>';
		yearHtml += '<ul id="sponsors' + sponsorsYears[key].year + '" class="notvisible">';
		
		var sponsors = sponsorsYears[key].sponsors;
		
		for (var key2 in sponsors) {
			yearHtml += '<li><a href="javascript:void(0);" onClick="javascript:showPlantingsForSponsor(\'' + sponsorsYears[key].year + '\',\'' + key2 + '\')" target="_self">' + sponsors[key2].name + '</a></li>';
		}
		
		yearHtml += '</ul>';
		yearHtml += '</li>';
		
		outputHtml = yearHtml + outputHtml;
	}
	
	$('#sponsors').html(outputHtml);
}

function getSponsors(year) {
	
	for (var yearKey in sponsorsYears) {
		
		if (sponsorsYears[yearKey].year == year) {
			return sponsorsYears[yearKey].sponsors;
		}
	}
}