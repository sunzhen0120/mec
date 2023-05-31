package com.njupt.constant;

public class ApiUrlConstant {

    public final static String KEYSTONE_AUTH_USER = "/v3/auth/tokens";
    public final static String KEYSTONE_AUTH_USER1= "/v3/auth/tokens";

    public final static String NOVA_LIST_SERVERS = "/v2.1/servers/detail";
    public final static String NOVA_SHOW_SERVER_DETAIL = "/v2.1/servers/";
    public final static String NOVA_SHOW_SERVER_DELETE = "/v2.1/servers/";
    public final static String NOVA_SERVER_ACTION = "/v2.1/servers/{server_id}/action";
    public final static String NOVA_SERVER_METADATA = "/v2.1/{server_id}/metadata";
    public final static String NOVA_SERVER_IPS= "/v2.1/{server_id}/ips";
    public final static String NOVA_CREATE_SERVER = "/v2.1/servers";
    public final static String NOVA_UPDATE_SERVER = "/v2.1/servers/";
    public final static String NOVA_FLAVORS = "/v2.1/flavors";

    public final static String NOVA_FLAVORS_LIST = "/v2.1/flavors/";


    public final static String NETWORK_PORT = "/v2.0/ports";
    public final static String NETWORK_PORT_DELETE = "/v2.0/ports/";
    public final static String NETWORK_VLAN = "/v2.0/networks";

    public final static String NETWORK_SUBNETS = "/v2.0/subnets";

    public final static String NETWORK_SUBNETS_DELETE = "/v2.0/subnets/";
    public final static String NETWORK_VLAN_DETAIL = "/v2.0/networks/";
    public final static String NETWORK_VLAN_DELETE = "/v2.0/networks/";
    public final static String NETWORK_VLAN_UPDATE = "/v2.0/networks/";

    public final static String GLANCE_GENERAL_IMAGE = "/v2/images";
    public final static String GLANCE_UPLOAD_BINARYDATA = "/v2/images/{IMAGEID}/file";
    public final static String GLANCE_GENERAL_CHANGE_IMAGE = "/v2/images/";
    public final static String GLANCE_DEACTIVATE_IMAGE_SUFFIX = "/actions/deactivate";
    public final static String GLANCE_REACTIVATE_IMAGE_SUFFIX = "/actions/reactivate";
    public final static String GLANCE_ADD_OR_DELETE_TAG = "/tags/";
    public final static String GLANCE_UPLOAD_OR_DOWNLOAD_IMAGE = "/file";

}
