/*
| Copyright 2013 Adobe
|
| Licensed under the Apache License, Version 2.0 (the "License");
| you may not use this file except in compliance with the License.
| You may obtain a copy of the License at
|
| http://www.apache.org/licenses/LICENSE-2.0
|
| Unless required by applicable law or agreed to in writing, software
| distributed under the License is distributed on an "AS IS" BASIS,
| WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
| See the License for the specific language governing permissions and
| limitations under the License.
*/
// Create the session store called "customstore"
if (!CQ_Analytics.DataLayerStoreMgr ) {

    // Create the session store as a JSONStore
    CQ_Analytics.DataLayerStoreMgr = CQ_Analytics.JSONStore.registerNewInstance("ddl-store");

	CQ_Analytics.DataLayerStoreMgr.currentId = "";

    // Function to load the data for the current user
    CQ_Analytics.DataLayerStoreMgr.loadData = function() {

        console.info("Loading DataLayerStoreMgr data");

        var authorizableId = CQ_Analytics.ProfileDataMgr.getProperty("authorizableId");
        var url = "/apps/aemdatalayer/components/loader.json";

        if ( (authorizableId !== CQ_Analytics.DataLayerStoreMgr.currentId) & CQ_Analytics.DataLayerStoreMgr.initialized ) {

            url = CQ_Analytics.Utils.addParameter(url, "authorizableId", authorizableId);

            try {

                var object = CQ.shared.HTTP.eval(url);
                if (object) { this.data = object; }
                    //this.data = {};
                    //for (var d in object) {
                    //    this.data[d] = object[d];
                    //}
                //}


            } catch(error) {
                console.log("Error", error);
            }

			CQ_Analytics.DataLayerStoreMgr.currentId = authorizableId;
            this.fireEvent("dataloaded");

        }

    };

    CQ_Analytics.CCM.addListener("configloaded", function() {

        CQ_Analytics.ProfileDataMgr.addListener("update", function() {
			this.loadData();

            console.info("ProfileDataMgr: update Event");
        }, CQ_Analytics.DataLayerStoreMgr);
        console.info("DataLayerStoreMgr: Configloaded event");

	}, CQ_Analytics.DataLayerStoreMgr);

    CQ_Analytics.DataLayerStoreMgr.addListener("initialize", function() {
		this.loadData();
        console.info("Custom Store Initialize Event");
    });

    CQ_Analytics.DataLayerStoreMgr.addListener("dataloaded", function() {
        console.info("Customstore data loaded");
        _satellite.track("DIRECTTEST");
    });

    CQ_Analytics.DataLayerStoreMgr.initialized = false;

    CQ_Analytics.DataLayerStoreMgr.getValue = function(service) {
        if (CQ_Analytics.DataLayerStoreMgr.data) {
            if (CQ_Analytics.DataLayerStoreMgr.data[service]) return  CQ_Analytics.DataLayerStoreMgr.data[service].value;
        }
        return "";
    }


}