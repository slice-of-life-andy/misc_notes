sort by function
http://solr/solr/collection_user/select?q=currentIndustry:401&rows=20&sort=pymk_user%28%29%20desc&gender=f&industry=104&fq={!frange%20l=1%20u=1}excludecf%28userID,4017%29

spatial query
http://solr/solr/collection_user/select?q=accountType:zy%20AND%20currentPositionsEnums:*&fq={!geofilt}&sfield=location&pt=39.9655395,116.0799914&d=151&fl=*,dist_floor:floor%28geodist%28%29%29,score&rows=20&sort=pymk_user%28%29%20desc&gender=f&industry=104&function=3

http://solr/solr/collection_status/select?q=*:*&fq={!geofilt}&sfield=location&pt=39.9655395,116.0799914&d=320&fl=*,dist_floor:floor%28geodist%28%29%29,score&rows=20&sort=near_posts_spatial%28floor%28geodist%28%29%29%29%20desc&gender=f&industry=101&cache=false


http://172.10.10.133:8000/solr/collection_company/select?q=*:*&wt=json&indent=true&sort=score%20desc&defType=edismax&bf=sum%28hasLogo,recip%28ms%28NOW,positionUpdate%29,3.16e-11,1,1%29%29&debugQuery=on

http://172.10.10.133:8000/solr/collection_company/select?q=*:*&wt=xml&indent=true&sort=score%20desc&defType=edismax&bf=sum%28hasLogo,recip%28ms%28NOW,positionUpdate%29,3.16e-11,1,1%29%29&debugQuery=on

http://solr/solr/collection_job2/select?q=is_visible%3Atrue+AND+is_stopped%3Afalse+AND+is_expired%3Afalse+AND+confirm_status%3Ac&start=0&rows=7&defType=edismax&bf=if%28exists%28query%28{!v%3D%27province%3A2%27}%29%29%2C100%2C0%29&sort=score+desc%2CcreateDay+desc&facet=true&facet.field=province



curl -i -H "Accept: application/json" -H "Content-Type: application/json" 'http://10.40.16.53/search/position/search?page=1&elt_per_page=1&search_source=App&search_type=User&requester_id=4017&provinceSort=2' 

boost
http://solr/solr/collection_job2/select?q=is_visible%3Atrue+AND+is_stopped%3Afalse+AND+is_expired%3Afalse+AND+confirm_status%3Ac&start=0&rows=2000&sort=createDay%20desc,score%20desc&fl=id,createDay,province&defType=edismax&bf=if%28exists%28query%28{!v=%27province:12%27}%29%29,100,0%29




http://solr/solr/collection_user/select?q=*:*&&uid=18917057&fq={!frange%20l=1%20u=3}followervsp%28userID%29&fl=userID
