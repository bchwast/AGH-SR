//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.9
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Smarthome;

public interface RadioIPrx extends SpeakerIPrx
{
    default RadioStation getStation()
    {
        return getStation(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default RadioStation getStation(java.util.Map<String, String> context)
    {
        return _iceI_getStationAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<RadioStation> getStationAsync()
    {
        return _iceI_getStationAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<RadioStation> getStationAsync(java.util.Map<String, String> context)
    {
        return _iceI_getStationAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<RadioStation> _iceI_getStationAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<RadioStation> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getStation", com.zeroc.Ice.OperationMode.Idempotent, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     RadioStation ret;
                     ret = RadioStation.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    default boolean setStation(RadioStation station)
        throws InvalidRadioStationException
    {
        return setStation(station, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean setStation(RadioStation station, java.util.Map<String, String> context)
        throws InvalidRadioStationException
    {
        try
        {
            return _iceI_setStationAsync(station, context, true).waitForResponseOrUserEx();
        }
        catch(InvalidRadioStationException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> setStationAsync(RadioStation station)
    {
        return _iceI_setStationAsync(station, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> setStationAsync(RadioStation station, java.util.Map<String, String> context)
    {
        return _iceI_setStationAsync(station, context, false);
    }

    /**
     * @hidden
     * @param iceP_station -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_setStationAsync(RadioStation iceP_station, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "setStation", com.zeroc.Ice.OperationMode.Idempotent, sync, _iceE_setStation);
        f.invoke(true, context, null, ostr -> {
                     RadioStation.ice_write(ostr, iceP_station);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_setStation =
    {
        InvalidRadioStationException.class
    };

    default java.util.List<RadioStation> getRadioStations()
    {
        return getRadioStations(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default java.util.List<RadioStation> getRadioStations(java.util.Map<String, String> context)
    {
        return _iceI_getRadioStationsAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.util.List<RadioStation>> getRadioStationsAsync()
    {
        return _iceI_getRadioStationsAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.util.List<RadioStation>> getRadioStationsAsync(java.util.Map<String, String> context)
    {
        return _iceI_getRadioStationsAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.util.List<RadioStation>> _iceI_getRadioStationsAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.util.List<RadioStation>> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getRadioStations", com.zeroc.Ice.OperationMode.Idempotent, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     java.util.List<RadioStation> ret;
                     ret = radioStationsHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RadioIPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), RadioIPrx.class, _RadioIPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RadioIPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), RadioIPrx.class, _RadioIPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RadioIPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), RadioIPrx.class, _RadioIPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RadioIPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), RadioIPrx.class, _RadioIPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static RadioIPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, RadioIPrx.class, _RadioIPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static RadioIPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, RadioIPrx.class, _RadioIPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default RadioIPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (RadioIPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default RadioIPrx ice_adapterId(String newAdapterId)
    {
        return (RadioIPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default RadioIPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (RadioIPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default RadioIPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (RadioIPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default RadioIPrx ice_invocationTimeout(int newTimeout)
    {
        return (RadioIPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default RadioIPrx ice_connectionCached(boolean newCache)
    {
        return (RadioIPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default RadioIPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (RadioIPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default RadioIPrx ice_secure(boolean b)
    {
        return (RadioIPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default RadioIPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (RadioIPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default RadioIPrx ice_preferSecure(boolean b)
    {
        return (RadioIPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default RadioIPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (RadioIPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default RadioIPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (RadioIPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default RadioIPrx ice_collocationOptimized(boolean b)
    {
        return (RadioIPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default RadioIPrx ice_twoway()
    {
        return (RadioIPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default RadioIPrx ice_oneway()
    {
        return (RadioIPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default RadioIPrx ice_batchOneway()
    {
        return (RadioIPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default RadioIPrx ice_datagram()
    {
        return (RadioIPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default RadioIPrx ice_batchDatagram()
    {
        return (RadioIPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default RadioIPrx ice_compress(boolean co)
    {
        return (RadioIPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default RadioIPrx ice_timeout(int t)
    {
        return (RadioIPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default RadioIPrx ice_connectionId(String connectionId)
    {
        return (RadioIPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default RadioIPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (RadioIPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::Smarthome::RadioI";
    }
}
