package com.zycus.orientdb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zycus.proc.entities.IProcess;
import com.zycus.proc.entities.IProcessInfo;
import com.zycus.proc.utils.JsonHelper;

import java.util.*;

/**
 * Created by megha.ray on 9/4/2017.
 */
public class Process
        implements IProcess
{
    private static final long serialVersionUID = 1L;
    private String ptype;
    private String name;
    private String cname;
    private LinkedList<String> tags = new LinkedList();
    private long pid;
    private IProcessInfo procInfo;

    public Process()
    {
        this.procInfo = new ProcessInfo();
    }

    public Process(long pid)
    {
        this.procInfo = new ProcessInfo();
        setPid(pid);
    }

    public static IProcess mockProcess()
    {
        return new Process();
    }

    public long getPid()
    {
        return this.pid;
    }

    public void setPid(long pid)
    {
        this.pid = pid;
    }

    public IProcess addTag(String tag)
    {
        if ((tag != null) && (!this.tags.contains(tag))) {
            this.tags.addFirst(tag);
        }
        return this;
    }

    public Deque<String> getTags()
    {
        return this.tags;
    }

    public IProcess setTags(Deque<String> tags)
    {
        if (tags != null)
        {
            Iterator<String> descItr = tags.descendingIterator();
            while (descItr.hasNext())
            {
                String tag = (String)descItr.next();
                if (tag != null) {
                    addTag(tag);
                }
            }
        }
        return this;
    }

    @JsonIgnore
    public IProcessInfo getProcInfo()
    {
        return this.procInfo;
    }

    public void setProcInfo(IProcessInfo procInfo)
    {
        this.procInfo = procInfo;
    }

    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Process process = (Process)o;

        return this.procInfo.equals(((Process)o).getProcInfo());
    }

    public int hashCode()
    {
        return (int)(this.pid ^ this.pid >>> 32);
    }

    public String toString()
    {
        return "Process [tags=" + this.tags + ", pid=" + this.pid + "]";
    }

    public String toJsonString()
    {
        return JsonHelper.getJsonString(toJsonNode());
    }

    public byte[] toJsonBytes()
    {
        return JsonHelper.getJsonBytes(toJsonNode());
    }

    public String getPtype()
    {
        return this.ptype;
    }

    public void setPtype(String ptype)
    {
        this.ptype = ptype;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCname(String cName)
    {
        this.cname = cName;
    }

    public String getCname()
    {
        return this.cname;
    }

    public JsonNode toJsonNode()
    {
        ObjectNode procInfoJsonNode = (ObjectNode)this.procInfo.toJsonNode();
        JsonNode procJsonNode = JsonHelper.valueToTree(this);

        Iterator<Map.Entry<String, JsonNode>> itr1 = procJsonNode.fields();
        while (itr1.hasNext())
        {
            Map.Entry<String, JsonNode> e = (Map.Entry)itr1.next();
            procInfoJsonNode.putPOJO((String)e.getKey(), e.getValue());
        }
        return procInfoJsonNode;
    }

    public int compareTo(IProcess o)
    {
        return Long.compare(getPid(), o.getPid());
    }

    public static class ProcessInfo
            implements IProcessInfo
    {
        private static final long serialVersionUID = 1L;
        private static final String UNKNOWN = "?";
        long virtual = 0L;
        long resident = 0L;
        String startTime;
        long userTime = 0L;
        long kernelTime = 0L;
        long uid = 0L;
        long gid = 0L;
        long euid = 0L;
        long egid = 0L;
        long totalFd = 0L;
        String name = "?";
        String cwd = "?";
        String env;
        String fqdn;
        String[] processArgs = null;
        Map<String, ?> procEnv = new HashMap();
        Map<String, Object> extensions;
        private String address;

        public void setProperty(String key, Object val)
        {
            if (this.extensions == null) {
                this.extensions = new HashMap();
            }
            this.extensions.put(key, val);
        }

        public String getAddress()
        {
            return this.address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getFqdn()
        {
            return this.fqdn;
        }

        public void setFqdn(String fqdn)
        {
            this.fqdn = fqdn;
        }

        public long getVirtual()
        {
            return this.virtual;
        }

        public void setVirtual(long size)
        {
            this.virtual = size;
        }

        public long getResident()
        {
            return this.resident;
        }

        public void setResident(long resident)
        {
            this.resident = resident;
        }

        public String getStartTime()
        {
            return this.startTime;
        }

        public void setStartTime(String startTime)
        {
            this.startTime = startTime;
        }

        public long getUserTime()
        {
            return this.userTime;
        }

        public void setUserTime(long userTime)
        {
            this.userTime = userTime;
        }

        public long getKernelTime()
        {
            return this.kernelTime;
        }

        public void setKernelTime(long kernelTime)
        {
            this.kernelTime = kernelTime;
        }

        public long getUid()
        {
            return this.uid;
        }

        public void setUid(long uid)
        {
            this.uid = uid;
        }

        public long getGid()
        {
            return this.gid;
        }

        public void setGid(long gid)
        {
            this.gid = gid;
        }

        public long getEuid()
        {
            return this.euid;
        }

        public void setEuid(long euid)
        {
            this.euid = euid;
        }

        public long getEgid()
        {
            return this.egid;
        }

        public void setEgid(long egid)
        {
            this.egid = egid;
        }

        public long getTotalFd()
        {
            return this.totalFd;
        }

        public void setTotalFd(long totalFd)
        {
            this.totalFd = totalFd;
        }

        public String getName()
        {
            return this.name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getCwd()
        {
            return this.cwd;
        }

        public void setCwd(String cwd)
        {
            this.cwd = cwd;
        }

        public String[] getProcessArgs()
        {
            return this.processArgs;
        }

        public void setProcessArgs(String[] processArgs)
        {
            this.processArgs = processArgs;
        }

        public Map<String, ?> getProcEnv()
        {
            return this.procEnv;
        }

        public void setProcEnv(Map<String, ?> procEnv)
        {
            this.procEnv = procEnv;
        }

        public Map<String, Object> toMap()
        {
            Map<String, Object> map = new HashMap();

            String strsize = String.valueOf(getVirtual());
            if (!"-1".equals(strsize)) {
                map.put("size", strsize);
            }
            String strresident = String.valueOf(getResident());
            if (!"-1".equals(strresident)) {
                map.put("residentmem", strresident);
            }
            String strstarttime = String.valueOf(getStartTime());
            if (!"-1".equals(strstarttime)) {
                map.put("starttime", strstarttime);
            }
            String strusertime = String.valueOf(getUserTime());
            if (!"-1".equals(strusertime)) {
                map.put("usertime", strusertime);
            }
            String strkerneltime = String.valueOf(getKernelTime());
            if (!"-1".equals(strkerneltime)) {
                map.put("kerneltime", strkerneltime);
            }
            String struid = String.valueOf(getUid());
            if (!"-1".equals(struid)) {
                map.put("uid", struid);
            }
            String strgid = String.valueOf(getGid());
            if (!"-1".equals(strgid)) {
                map.put("gid", strgid);
            }
            String streuid = String.valueOf(getEuid());
            if (!"-1".equals(streuid)) {
                map.put("euid", streuid);
            }
            String stregid = String.valueOf(getEgid());
            if (!"-1".equals(stregid)) {
                map.put("egid", stregid);
            }
            String strfdname = String.valueOf(getTotalFd());
            if (!"-1".equals(strfdname)) {
                map.put("fd", strfdname);
            }
            String strname = String.valueOf(getName());
            if (!"-1".equals(strname)) {
                map.put("name", strname);
            }
            String strcwd = String.valueOf(getCwd());
            if (!"-1".equals(strcwd)) {
                map.put("cwd", strcwd);
            }
            return map;
        }

        public Map<String, Object> getExtensions()
        {
            return this.extensions;
        }

        public String getEnv()
        {
            return this.env;
        }

        public void setEnv(String env)
        {
            this.env = env;
        }

        public void setExtensions(Map<String, Object> extensions)
        {
            this.extensions = extensions;
        }

        public String toJsonString()
        {
            return JsonHelper.getJsonString(toJsonNode());
        }

        public byte[] toJsonBytes()
        {
            return JsonHelper.getJsonBytes(toJsonNode());
        }

        public boolean equals(Object o)
        {
            if (this == o) {
                return true;
            }
            if ((o == null) || (getClass() != o.getClass())) {
                return false;
            }
            ProcessInfo that = (ProcessInfo)o;
            if (!this.cwd.equals(that.cwd)) {
                return false;
            }
            if (!this.address.equals(that.address)) {
                return false;
            }
            return this.env.equals(that.env);
        }

        public int hashCode()
        {
            int result = this.cwd.hashCode();
            result = 31 * result + this.address.hashCode();
            result = 31 * result + this.env.hashCode();
            return result;
        }

        public String toString()
        {
            return

                    "ProcInfo [size=" + this.virtual + ", resident=" + this.resident + ", startTime=" + this.startTime + ", userTime=" + this.userTime + ", kernelTime=" + this.kernelTime + ", uid=" + this.uid + ", gid=" + this.gid + ", euid=" + this.euid + ", egid=" + this.egid + ", totalFd=" + this.totalFd + ", name=" + this.name + ", cwd=" + this.cwd + ", processArgs=" + Arrays.toString(this.processArgs) + ", procEnv=" + this.procEnv + "]";
        }

        public JsonNode toJsonNode()
        {
            JsonNode rootNode = JsonHelper.valueToTree(this);
            ObjectNode rootObjNode = (ObjectNode)rootNode;
            ((ObjectNode)rootNode).remove("extensions");
            if ((this.extensions != null) && (!this.extensions.isEmpty())) {
                for (String key : this.extensions.keySet()) {
                    rootObjNode.putPOJO(key, this.extensions.get(key));
                }
            }
            return rootNode;
        }
    }
}

