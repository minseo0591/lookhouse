var map;
var polygons = []; // 폴리곤 객체를 저장할 배열

function initialize(_map) {
    map = _map;
}

function drawPolygon() {
    $.getJSON("/json/geo.json", function(geojson) {
        var data = geojson.features;
        var name = ''; // 행정구 명
        var code = '';

        $.each(data, function(index, val) {
            name = val.properties.CTP_ENG_NM;
            code = val.properties.CTPRVN_CD;

            if (val.geometry.type == "MultiPolygon") {
                displayArea(name, code, val.geometry.coordinates, true);
            } else {
                displayArea(name, code, val.geometry.coordinates, false);
            }
        });
    });
}


function makePolygon(coordinates) {
    var polygonPath = [];

    $.each(coordinates[0], function(index, coordinate) {
        polygonPath.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
    });

    var polygon = new kakao.maps.Polygon({
        path:polygonPath, // 그려질 다각형의 좌표 배열입니다
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: '#39DE2A', // 선의 색깔입니다
        strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'longdash', // 선의 스타일입니다
        fillColor: '#A2FF99', // 채우기 색깔입니다
        fillOpacity: 0.7, // 채우기 불투명도 입니다
        map: map
    });

    polygons.push(polygon); // 생성된 폴리곤 객체를 배열에 저장
    return polygon;
}

function makeMultiPolygon(coordinates) {
    var polygonPath = [];

    $.each(coordinates, function(index, val2) {
        var coordinates2 = [];

        $.each(val2[0], function(index2, coordinate) {
            coordinates2.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
        });

        polygonPath.push(coordinates2);
    });

    var polygon = new kakao.maps.Polygon({
        path:polygonPath, // 그려질 다각형의 좌표 배열입니다
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: '#39DE2A', // 선의 색깔입니다
        strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'longdash', // 선의 스타일입니다
        fillColor: '#A2FF99', // 채우기 색깔입니다
        fillOpacity: 0.7, // 채우기 불투명도 입니다
        map: map
    });

    polygons.push(polygon); // 생성된 폴리곤 객체를 배열에 저장
    return polygon;
}

// Params: 행정구 명, 코드, 좌표, 멀티폴리곤 유무
function displayArea(name, code, coordinates, multi) {
    var polygon;
    if (multi) {
        polygon = makeMultiPolygon(coordinates);
    } else {
        polygon = makePolygon(coordinates);
    }

    // 다각형에 행정구 정보 저장
    polygon.name = name;
    polygon.code = code;

     // 다각형에 이벤트 리스너 등록
        kakao.maps.event.addListener(polygon, 'mouseover', function() {
            polygon.setOptions({
                fillColor: '#FF0000',
                strokeWeight: 5
            });
        });

        kakao.maps.event.addListener(polygon, 'mouseout', function() {
            polygon.setOptions({
                fillColor: '#A2FF99',
                strokeWeight: 3
            });
        });

        kakao.maps.event.addListener(polygon, 'click', function() {
            hideAllPolygons();
            zoomToPolygon(polygon);
        });

        polygon.setMap(map);
}

function hideAllPolygons() {
    $.each(polygons, function(index, polygon) {
        polygon.setOptions({
            fillOpacity: 0,
            strokeOpacity: 0
        });
    });
}

function zoomToPolygon(polygon) {
    var bounds = new kakao.maps.LatLngBounds();
    var path = polygon.getPath();

    for (var i = 0; i < path.length; i++) {
        bounds.extend(path[i]);
    }

    map.setBounds(bounds);
}